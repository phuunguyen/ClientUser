package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.adapter.CartAdapter;
import com.example.clientuser.adapter.CuaHangAdapter;
import com.example.clientuser.model.Cart;
import com.example.clientuser.model.CuaHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {


    ListView listViewCart;
    ImageButton imgButtonLeft;
    TextView txtTotalPrice;

    ArrayList<Cart> data = new ArrayList<>();
    CartAdapter adapter = null;
    ArrayList<String> listProduct = null;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        if (getArrayList("listProduct") != null) {
            listProduct = getArrayList("listProduct");
        }
        loadData();
        adapter = new CartAdapter(this, R.layout.listview_giohang, data);
        listViewCart.setAdapter(adapter);
        imgButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControl() {
        listViewCart = (ListView) findViewById(R.id.lvCart);
        imgButtonLeft = (ImageButton) findViewById(R.id.imgButtonLeft);
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);

    }

    private void loadData() {
        mData.child("Product").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Cart cart = new Cart();
                cart.setProductImage(dataSnapshot.child("Product_image").getValue().toString());
                cart.setIdProduct(dataSnapshot.child("Id_product").getValue().toString());
                cart.setProductName(dataSnapshot.child("Product_name").getValue().toString());
                cart.setProductPrice(Double.parseDouble(dataSnapshot.child("Price").getValue().toString()));
                cart.setQuantity(1);
                for (int i = 0; i < listProduct.size(); i++) {
                    if (cart.getIdProduct().equals(listProduct.get(i))) {
                        data.add(cart);
                    }
                }
                adapter.notifyDataSetChanged();

                //Tính tổng tiền
                double tong = 0.0;
                for (int i = 0; i < data.size(); i++) {
                    tong += data.get(i).getQuantity() * data.get(i).getProductPrice();
                }
                txtTotalPrice.setText(tong + " VND");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public ArrayList<String> getArrayList(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

}
