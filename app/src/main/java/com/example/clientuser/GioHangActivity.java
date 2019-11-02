package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.adapter.CartAdapter;
import com.example.clientuser.adapter.DoUongAdapter;
import com.example.clientuser.model.Cart;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GioHangActivity extends AppCompatActivity {


    RecyclerView listViewCart;
    ImageView imgButtonLeft, imgListOrder;
    TextView txtTotalPrice;
    Button btnOrder;

    ArrayList<Cart> data = new ArrayList<>();
    CartAdapter adapter = null;
    ArrayList<String> listProduct = null;
    List<String> listWithoutDuplicateElements;
    DoUongAdapter doUongAdapter;

    LinearLayout coordinatorLayout;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDataCart = database.getReference("Cart");
    DatabaseReference mDataMaxID = database.getReference("MaxID");
    DatabaseReference mDataProduct = database.getReference("Product");

    int countCart;
    double tong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        doUongAdapter = new DoUongAdapter(GioHangActivity.this);
        if (getArrayList("listProduct") != null) {
            listProduct = getArrayList("listProduct");
            loadData();
        }
        adapter = new CartAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listViewCart.setLayoutManager(layoutManager);
        listViewCart.setAdapter(adapter);
        enableSwipeToDeleteAndUndo();
        imgButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mDataMaxID.child("MaxID_Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    countCart = 0;
                } else {
                    countCart = Integer.parseInt(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    countCart++;
                    addCartToDB();
                    Toast.makeText(GioHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                    data.clear();
                    adapter.notifyDataSetChanged();
                    txtTotalPrice.setText(0.0 + "");
                }catch (Exception e){
                    Log.d("Giohang", e.getMessage());
                }
            }
        });

        imgListOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHangActivity.this, ListOrderActivity.class));
            }
        });
    }

    private void setControl() {
        listViewCart = (RecyclerView) findViewById(R.id.lvCart);
        imgButtonLeft = (ImageView) findViewById(R.id.imgButtonLeft);
        imgListOrder = (ImageView) findViewById(R.id.imgListOrder);
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        coordinatorLayout = (LinearLayout) findViewById(R.id.coordinatorLayout);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Cart item = adapter.getData().get(position);
                doUongAdapter.deleteItem(item.getIdProduct());
                adapter.removeItem(position);


                tong = 0.0;

                for (int j = 0; j < data.size(); j++) {
                    tong += data.get(j).getQuantity() * data.get(j).getProductPrice();
                }
                txtTotalPrice.setText(tong + " VND");


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Đã xóa đồ uống khỏi danh sách", Snackbar.LENGTH_LONG);
                snackbar.setAction("Hoàn tác", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doUongAdapter.restoreItem(item.getIdProduct());
                        adapter.restoreItem(item, position);

                        tong = 0.0;

                        for (int j = 0; j < data.size(); j++) {
                            tong += data.get(j).getQuantity() * data.get(j).getProductPrice();
                        }
                        txtTotalPrice.setText(tong + " VND");

                        listViewCart.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(ContextCompat.getColor(GioHangActivity.this, R.color.colorPrimary));
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(listViewCart);
    }

    private void loadData() {
        mDataProduct.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Cart cart = new Cart();
                cart.setProductImage(dataSnapshot.child("product_image").getValue().toString());
                cart.setIdProduct(dataSnapshot.child("id_product").getValue().toString());
                cart.setProductName(dataSnapshot.child("product_name").getValue().toString());
                cart.setProductPrice(Double.parseDouble(dataSnapshot.child("price").getValue().toString()));
                Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
                for (int i = 0; i < listProduct.size(); i++) {
                    if (cart.getIdProduct().equals(listProduct.get(i))) {
                        addElement(map, Integer.parseInt(listProduct.get(i)));
                    }
                }

                for (Integer key : map.keySet()) {
                    cart.setQuantity(map.get(key));
                }

                // Constructing HashSet using listWithDuplicateElements
                Set<String> set = new HashSet<String>(listProduct);

                // Constructing listWithoutDuplicateElements using set
                listWithoutDuplicateElements = new ArrayList<String>(set);
                for (int i = 0; i < listWithoutDuplicateElements.size(); i++) {
                    if (cart.getIdProduct().equals(listWithoutDuplicateElements.get(i))) {
                        data.add(cart);
                    }
                }
                adapter.notifyDataSetChanged();

                //Tính tổng tiền
                tong = 0.0;
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
        SharedPreferences prefs = this.getSharedPreferences("SHARED_PREFERENCES_LISTPRODUCT", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void addCartToDB() {
        SharedPreferences sp_IDUSER = getSharedPreferences("SHARED_PREFERENCES_IDUSER", Context.MODE_PRIVATE);
        final String idUser = sp_IDUSER.getString("IDUSER", null);
        SharedPreferences sp_IDSTORE = getSharedPreferences("SHARED_PREFERENCES_IDSTORE", Context.MODE_PRIVATE);
        final String idStore = sp_IDSTORE.getString("IDSTORE", null);
        mDataCart.child("Cart" + countCart).child("id_donhang").setValue("Cart" + countCart);
        mDataCart.child("Cart" + countCart).child("id_user").setValue(idUser);
        mDataCart.child("Cart" + countCart).child("id_store").setValue(idStore);
        for (int i = 0; i < data.size(); i++) {
            mDataCart.child("Cart" + countCart).child("id_product").child(String.valueOf(i)).setValue(data.get(i).getIdProduct());
            mDataCart.child("Cart" + countCart).child("quantity").child(String.valueOf(i)).setValue(data.get(i).getQuantity());
        }
        mDataCart.child("Cart" + countCart).child("price").setValue(tong);
        mDataCart.child("Cart" + countCart).child("status").setValue("yes");
        mDataCart.child("Cart" + countCart).child("delivery").setValue("no");
        mDataCart.child("Cart" + countCart).child("check").setValue("no");
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        mDataCart.child("Cart" + countCart).child("ngaytao").setValue(ft.format(date));

        mDataMaxID.child("MaxID_Cart").setValue(countCart);
    }

    public static void addElement(Map<Integer, Integer> map, int element) {
        if (map.containsKey(element)) {
            int count = map.get(element) + 1;
            map.put(element, count);
        } else {
            map.put(element, 1);
        }
    }

}
