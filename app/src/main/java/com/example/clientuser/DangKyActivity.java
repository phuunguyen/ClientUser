package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.database.object.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangKyActivity extends AppCompatActivity {

    TextView txtNhapDeDN;
    EditText edtName, edtPhone, edtEmail, edtAddress, edtPass, edtPass1;
    Button btnDK;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    DatabaseReference Table_User = mData.child("Users");
    Users users = new Users();
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        setControl();
        setEvent();

    }

    public void setEvent() {
        mData.child("MaxID").child("MaxID_Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    i = 0;
                } else {
                    i = Integer.parseInt(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String pass1 = edtPass1.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Enter name address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(address)){
                    Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(getApplicationContext(), "Enter phone address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass1.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                i++;

                users.setName(edtName.getText().toString());
                users.setPhone(Integer.parseInt(edtPhone.getText().toString()));
                users.setEmail(edtEmail.getText().toString());
                users.setAddress(edtAddress.getText().toString());
                users.setPassword(edtPass.getText().toString());
                users.setId_Users("User" + i);
                if(edtPass.getText().toString().equals(edtPass1.getText().toString())){
                    Table_User.child("User" + i).setValue(users);
                    Toast.makeText(getApplicationContext(),"Dang ki thanh cong", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Sai mat khau vui long nhap lai", Toast.LENGTH_SHORT).show();
                }
                mData.child("MaxID_Users").setValue(i);


            }
        });
        txtNhapDeDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setControl() {
        txtNhapDeDN = (TextView) findViewById(R.id.txtNhapDeDN);
        btnDK = (Button) findViewById(R.id.btnDangKy);
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPass = (EditText) findViewById(R.id.edtPassWord);
        edtPass1 = (EditText) findViewById(R.id.edtPassWord1);
    }
}
