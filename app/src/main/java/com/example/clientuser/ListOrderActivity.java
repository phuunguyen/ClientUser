package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clientuser.adapter.OrderAdapter;
import com.example.clientuser.model.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListOrderActivity extends AppCompatActivity {

    private ListView lvOrder;
    ArrayList<Order> data = new ArrayList<>();
    OrderAdapter adapter;
    ImageView imgButtonLeft;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvOrder = (ListView) findViewById(R.id.lvOrder);
        imgButtonLeft = (ImageView)findViewById(R.id.imgButtonLeft);
    }

    private void setEvent() {
        imgButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapter = new OrderAdapter(this, R.layout.listview_item_order, data);
        lvOrder.setAdapter(adapter);

        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDCART", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("IDCART", data.get(position).getTxtMaDonHang()).apply();
                startActivity(new Intent(ListOrderActivity.this, TrangThaiActivity.class));
            }
        });
    }

    private void loadData() {
        final SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDUSER", Context.MODE_PRIVATE);
        final String idUser = sharedPreferences.getString("IDUSER", null);
        mData.child("Cart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = new Order();
                try {
                    if (dataSnapshot.child("id_user").getValue().toString().equals(idUser)) {
                        order.setImageOrder(R.drawable.logoapp);
                        order.setTxtMaDonHang(dataSnapshot.child("id_donhang").getValue().toString());
                        order.setTxtNgayTao(dataSnapshot.child("ngaytao").getValue().toString());
                        data.add(order);
                    }
                } catch (Exception e) {
                    Toast.makeText(ListOrderActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        data.clear();
        loadData();
    }
}
