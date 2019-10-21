package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.clientuser.adapter.CuaHangAdapter;
import com.example.clientuser.model.CuaHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListCuaHangActivity extends AppCompatActivity {

    ListView lvDSCH;
    ImageButton imgUser;
    ArrayList<CuaHang> data = new ArrayList<>();
    CuaHangAdapter adapter = null;
    String idUser = "";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cua_hang);
        mData = database.getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        adapter = new CuaHangAdapter(this, R.layout.listview_item_cuahang, data);
        lvDSCH.setAdapter(adapter);
        loadData();

        lvDSCH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListCuaHangActivity.this, ListDoUongActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDSTORE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("IDSTORE", data.get(position).getIdStore()).apply();
                startActivity(intent);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThongTinUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        lvDSCH = (ListView) findViewById(R.id.lvDSCH);
        imgUser = (ImageButton) findViewById(R.id.btnUser);
    }

    private void loadData() {
        mData.child("Store").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                CuaHang store = new CuaHang();
                store.setIdStore(dataSnapshot.child("id_store").getValue().toString());
                store.setImageCuaHang(dataSnapshot.child("image").getValue().toString());
                store.setShopName(dataSnapshot.child("store_Name").getValue().toString());
                store.setShopAddress(dataSnapshot.child("address").getValue().toString());
                store.setRating((double) Math.round(Double.parseDouble(dataSnapshot.child("rating").getValue().toString()) * 10) / 10);
                data.add(store);
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
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("SHARED_PREFERENCES_LISTPRODUCT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("listProduct").apply();
    }
}
