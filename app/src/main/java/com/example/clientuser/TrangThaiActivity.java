package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.clientuser.adapter.CartAdapter;
import com.example.clientuser.adapter.TrangThaiAdapter;
import com.example.clientuser.model.Cart;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrangThaiActivity extends AppCompatActivity {
    private RecyclerView lvDonHang;
    ImageView btnDanhGia, btnBack, btnCheck, btnStatus, btnDelivery;

    ArrayList<Cart> data = new ArrayList<>();
    CartAdapter adapter = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData;
    DatabaseReference mDataCart = database.getReference("Cart");
    DatabaseReference mDataMaxID = database.getReference("MaxID");
    DatabaseReference mDataProduct = database.getReference("Product");

    ArrayList<String> listProductID = new ArrayList<>();
    ArrayList<String> listQuantityProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_thai);
        mData = database.getReference();

        setControl();

        setEvent();
    }

    private void setEvent() {

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(TrangThaiActivity.this, DanhGiaActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getCartProduct();
        adapter = new CartAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        lvDonHang.setLayoutManager(layoutManager);
        lvDonHang.setAdapter(adapter);
    }

    private void setControl() {
        btnDanhGia = (ImageView) findViewById(R.id.btnDanhGia);
        lvDonHang = (RecyclerView) findViewById(R.id.lvDH);
        btnBack = (ImageView) findViewById(R.id.imgButtonLeft);
        btnCheck = (ImageView) findViewById(R.id.btnCheck);
        btnDelivery = (ImageView) findViewById(R.id.btnDelivery);
        btnStatus = (ImageView) findViewById(R.id.btnStatus);
    }

    private void getCartProduct() {
        final SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDCART", Context.MODE_PRIVATE);
        final String cartID = sharedPreferences.getString("IDCART", null);
        mDataCart.child(cartID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("id_product").getChildren()) {
                    String product = snapshot.getValue().toString();
                    listProductID.add(product);
                }
                Log.e("---", listProductID.toString());
                for (DataSnapshot snapshot : dataSnapshot.child("quantity").getChildren()) {
                    String quantity = snapshot.getValue().toString();
                    listQuantityProduct.add(quantity);
                }
                Log.e("---", listQuantityProduct.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDataProduct.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Cart cart = new Cart();
                cart.setProductImage(dataSnapshot.child("product_image").getValue().toString());
                cart.setIdProduct(dataSnapshot.child("id_product").getValue().toString());
                cart.setProductName(dataSnapshot.child("product_name").getValue().toString());
                cart.setProductPrice(Double.parseDouble(dataSnapshot.child("price").getValue().toString()));

                for (int i = 0; i < listProductID.size(); i++) {
                    if (cart.getIdProduct().equals(listProductID.get(i))) {
                        cart.setQuantity(Integer.parseInt(listQuantityProduct.get(i)));
                        data.add(cart);
                    }
                }
                adapter.notifyDataSetChanged();
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

        mDataCart.child(cartID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.child("status").getValue().toString().equals("yes")) {
                        btnStatus.setBackgroundResource(R.drawable.hlcircle);
                    }

                    if (dataSnapshot.child("check").getValue().toString().equals("yes")) {
                        btnCheck.setBackgroundResource(R.drawable.hlcircle);
                    }

                    if (dataSnapshot.child("delivery").getValue().toString().equals("yes")) {
                        btnDelivery.setBackgroundResource(R.drawable.hlcircle);
                    }

                    if (dataSnapshot.child("finish").getValue().toString().equals("yes")) {
                        btnDanhGia.setBackgroundResource(R.drawable.hlcircle);
                    }
                } catch (Exception e) {
                    Log.d("Status", e.getMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
