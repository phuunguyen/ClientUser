package com.example.clientuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.clientuser.adapter.TrangThaiAdapter;
import com.example.clientuser.model.Cart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TrangThaiActivity extends AppCompatActivity {
    private ListView lvDonHang;
    ImageView btnDanhGia, btnBack;

    ArrayList<Cart> data = new ArrayList<>();
    TrangThaiAdapter adapter = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData;

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

        loadData();
        adapter = new TrangThaiAdapter(this, R.layout.listview_item_order, data);
        lvDonHang.setAdapter(adapter);

    }

    private void setControl() {
        btnDanhGia = (ImageView) findViewById(R.id.btnDanhGia);
        lvDonHang = (ListView) findViewById(R.id.lvDH);
        btnBack = (ImageView) findViewById(R.id.imgButtonLeft);
    }

    private void loadData() {

    }


}
