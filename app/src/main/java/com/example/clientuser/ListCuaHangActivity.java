package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.adapter.CuaHangAdapter;
import com.example.clientuser.adapter.DoUongAdapter;
import com.example.clientuser.model.CuaHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListCuaHangActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtT;
    ListView lvDSCH;
    ImageButton imgUser;
    ArrayList<CuaHang> data = new ArrayList<>();
    CuaHangAdapter adapter = null;
    AutoCompleteTextView edtSearch;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData;

    private ArrayList<String> listStoreName = new ArrayList<>();
    private ArrayAdapter adapterStoreName;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cua_hang);
        mData = database.getReference();
        setControl();
        setEvent();
        initPreferences();

    }
    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
    }

    private void setEvent() {
        adapter = new CuaHangAdapter(this, R.layout.listview_item_cuahang, data);
        lvDSCH.setAdapter(adapter);
        loadNameStore();
        loadData();
        edtSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edtSearch.getRight() - edtSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        data.clear();
                        if (edtSearch.getText().toString().isEmpty()) {
                            loadData();
                        } else {
                            loadDataSearch(edtSearch.getText().toString());
                        }
                        return true;
                    }
                }
                return false;
            }
        });

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
        edtSearch = (AutoCompleteTextView) findViewById(R.id.edtSearch);
    }

    private void loadData() {
        mData.child("Store").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                final CuaHang store = new CuaHang();
                store.setIdStore(dataSnapshot.child("id_Store").getValue().toString());
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

    private void loadDataSearch(final String storeName) {
        mData.child("Store").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                final CuaHang store = new CuaHang();
                if (dataSnapshot.child("store_Name").getValue().toString().equals(storeName)) {
                    store.setIdStore(dataSnapshot.child("id_Store").getValue().toString());
                    store.setImageCuaHang(dataSnapshot.child("image").getValue().toString());
                    store.setShopName(dataSnapshot.child("store_Name").getValue().toString());
                    store.setShopAddress(dataSnapshot.child("address").getValue().toString());
                    store.setRating((double) Math.round(Double.parseDouble(dataSnapshot.child("rating").getValue().toString()) * 10) / 10);
                    data.add(store);
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

    private void loadNameStore() {
        mData.child("Store").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listStoreName.add(dataSnapshot.child("store_Name").getValue().toString());
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
        adapterStoreName = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listStoreName);
        edtSearch.setAdapter(adapterStoreName);
        edtSearch.setThreshold(1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("SHARED_PREFERENCES_LISTPRODUCT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().apply();

        DoUongAdapter doUongAdapter = new DoUongAdapter(getApplicationContext());
        doUongAdapter.clearList();
    }

    @Override
    public void onClick(View view) {
        if(view == editor.putString("SHARED_PREFERENCES_IDUSER", "")){
            editor.commit();
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấp vào phím quay về lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
