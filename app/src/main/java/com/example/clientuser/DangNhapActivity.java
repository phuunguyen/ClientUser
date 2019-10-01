package com.example.clientuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {

    Button btnDangNhap;
    TextView txtNDDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        setControl();
        setEvent();


    }
    private void setControl() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        txtNDDK = findViewById(R.id.txtNhapDeDK);
    }
    private void setEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, ListCuaHangActivity.class);
                startActivity(intent);

            }
        });

        txtNDDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              final   Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}
