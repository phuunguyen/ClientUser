package com.example.clientuser;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class GioHangActivity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
    }
}
