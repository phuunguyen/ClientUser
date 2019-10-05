package com.example.clientuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clientuser.database.object.Cart;

import java.util.ArrayList;

public class TrangThaiActivity extends AppCompatActivity {
    private ListView listViewTrangThai;
    ImageButton btnDanhGia;
    TextView txtMaDonhang;
    ImageView imgRating, imgSP;
    ArrayList<Cart> data = new ArrayList<>();
    ArrayAdapter<CuaHangAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_thai);

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
    }

    private void setControl() {
        btnDanhGia = findViewById(R.id.btnDanhGia);
        txtMaDonhang = findViewById(R.id.txtMaDonHang);
        imgRating = findViewById(R.id.imgRating);
        imgSP = findViewById(R.id.imgSP);
    }


}
