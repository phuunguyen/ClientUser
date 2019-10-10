package com.example.clientuser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clientuser.adapter.DoUongAdapter;
import com.example.clientuser.adapter.ViewPagerAdapter;
import com.example.clientuser.fragments.AllProductFragment;
import com.example.clientuser.fragments.CoffeeFragment;
import com.example.clientuser.model.Product;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

//import com.example.clientuser.database.object.Product;

public class ListDoUongActivity extends AppCompatActivity {


    ImageButton imgGioHang;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton btnBack;
    TextView tvShopName, tvShopAddress, tvPhone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_do_uong);
        setControl();
        setEvent();
    }

    public void setControl() {
        btnBack = (ImageButton) findViewById(R.id.imgButtonLeft);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvShopAddress = (TextView) findViewById(R.id.tvAddressShop);
        tvShopName = (TextView) findViewById(R.id.tvNameShop);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        imgGioHang = (ImageButton)findViewById(R.id.btnCart);
    }

    public void setEvent() {
        //Load tablayout
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        //Load dữ liệu của cửa hàng
        loadShopInfo();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }


    private void loadShopInfo() {
        //Load dữ liệu của hàng lên textView
        Intent intent = getIntent();
        String idStore = intent.getStringExtra("idStore");
        databaseReference.child("Store").child(idStore).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvPhone.setText(dataSnapshot.child("phone").getValue().toString());
                tvShopAddress.setText(dataSnapshot.child("address").getValue().toString());
                tvShopName.setText(dataSnapshot.child("shopName").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllProductFragment(), "Tất cả");
        adapter.addFragment(new CoffeeFragment(), "Coffee");
        viewPager.setAdapter(adapter);
    }
}
