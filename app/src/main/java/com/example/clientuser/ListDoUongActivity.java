package com.example.clientuser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.adapter.DoUongAdapter;
import com.example.clientuser.adapter.ViewPagerAdapter;
import com.example.clientuser.fragments.AllProductFragment;
import com.example.clientuser.fragments.BubbleTeaFragment;
import com.example.clientuser.fragments.CoffeeFragment;
import com.example.clientuser.fragments.ToppingFragment;
import com.example.clientuser.model.Product;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
    private ImageView btnBack;
    TextView tvShopName, tvShopAddress, tvPhone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    private String[] menu_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_do_uong);
        setControl();
        setEvent();
    }

    public void setControl() {
        btnBack = (ImageView) findViewById(R.id.imgButtonLeft);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvShopAddress = (TextView) findViewById(R.id.tvAddressShop);
        tvShopName = (TextView) findViewById(R.id.tvNameShop);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        imgGioHang = (ImageButton) findViewById(R.id.btnCart);
    }

    public void setEvent() {
        menu_product = getResources().getStringArray(R.array.menu_product_value);
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
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDSTORE", Context.MODE_PRIVATE);
        String idStore = sharedPreferences.getString("IDSTORE", null);
        databaseReference.child("Store").child(idStore).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvPhone.setText(dataSnapshot.child("phone").getValue().toString());
                tvShopAddress.setText(dataSnapshot.child("address").getValue().toString());
                tvShopName.setText(dataSnapshot.child("store_Name").getValue().toString());
//                if (dataSnapshot.child("id_Store").getValue() == null) {
//                    new MaterialAlertDialogBuilder(new ContextThemeWrapper(getApplicationContext(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog))
//                            .setTitle("Không có dữ liệu")
//                            .setMessage("Cửa hàng này đã bị xóa")
//                            .setCancelable(false)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    onBackPressed();
//                                }
//                            }).show();
//                } else {
//                    tvPhone.setText(dataSnapshot.child("phone").getValue().toString());
//                    tvShopAddress.setText(dataSnapshot.child("address").getValue().toString());
//                    tvShopName.setText(dataSnapshot.child("store_Name").getValue().toString());
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllProductFragment(), menu_product[0]);
        adapter.addFragment(new CoffeeFragment(), menu_product[1]);
        adapter.addFragment(new BubbleTeaFragment(), menu_product[2]);
        adapter.addFragment(new ToppingFragment(), menu_product[3]);
        viewPager.setAdapter(adapter);
    }

}
