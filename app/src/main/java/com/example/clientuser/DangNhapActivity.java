package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.database.object.SaveCache;
import com.example.clientuser.database.object.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangNhap;
    TextView txtNDDK;
    EditText edtEmailDN, edtPassDN;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    //DatabaseReference Table_User = mData.child("Users");
    //Users users = new Users();
    private RadioButton rbGhiNho;
    boolean check = false;
    private SaveCache save;
    ArrayList<Users> arrUser = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        setControl();
        setEvent();
        save = new SaveCache(this);
        GetDataGhiNho();
    }

    private void setControl() {
        rbGhiNho = (RadioButton) findViewById(R.id.rbtGhinho);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        txtNDDK = (TextView) findViewById(R.id.txtNhapDeDK);
        edtEmailDN = (EditText) findViewById(R.id.edtEmailDN);
        edtPassDN = (EditText) findViewById(R.id.edtPassWordDN);
    }

    private void setEvent() {

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmailDN.getText().toString().trim();
                String pass = edtPassDN.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                mData.child("Users").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Users us = dataSnapshot.getValue(Users.class);
                        arrUser.add(us);

//                        Users us = dataSnapshot.getValue(Users.class);
//                            if (edtEmailDN.getText().toString().equals(us.getEmail()) && edtPassDN.getText().toString().equals(us.getPassword())) {
//                                if (rbGhiNho.isChecked()) {
//                                    CreateDataGhiNho(edtEmailDN.getText().toString(), edtPassDN.getText().toString(), true);
//                                } else {
//                                    CreateDataGhiNho("", "", false);
//
//                                }
//                                Toast.makeText(getApplicationContext(), "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
//                                String idDN = us.getId_Users();
//                                //Log.d("123", us.getId_Users());
//                                Intent intent = new Intent(DangNhapActivity.this, ListCuaHangActivity.class);
//                                intent.putExtra("idUser", idDN);
//                                startActivity(intent);
//
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
//                            }


//
                        for (int i = 0; i < arrUser.size(); i++){
                            if (arrUser.get(i).getEmail().equals(edtEmailDN.getText().toString()) && arrUser.get(i).getPassword().equals(edtPassDN.getText().toString())) {
                                if (rbGhiNho.isChecked()) {
                                    CreateDataGhiNho(edtEmailDN.getText().toString(), edtPassDN.getText().toString(), true);
                                } else {
                                    CreateDataGhiNho("", "", false);

                                }
                                Toast.makeText(getApplicationContext(), "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                                String idDN = arrUser.get(i).getId_Users();
                                Intent intent = new Intent(DangNhapActivity.this, ListCuaHangActivity.class);
                                intent.putExtra("idUser", idDN);
                                startActivity(intent);
                                finish();
                                break;

                            } else {
                                //Log.d("---", "ok1");
                                Toast.makeText(getApplicationContext(), "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                            }
                        }
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
        });
        rbGhiNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = !check;
                rbGhiNho.setChecked(check);
            }
        });

        txtNDDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

    void CreateDataGhiNho(String user, String pass, boolean check) {
        SharedPreferences sharedPreferences = getSharedPreferences("DataSaveAccount", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.putBoolean("BtnSave", check);
        editor.apply();

    }

    void GetDataGhiNho() {
        SharedPreferences sharedPreferences = getSharedPreferences("DataSaveAccount", MODE_PRIVATE);
        check = sharedPreferences.getBoolean("BtnSave", false);
        rbGhiNho.setChecked(check);
        edtEmailDN.setText(sharedPreferences.getString("user", ""));
        edtPassDN.setText(sharedPreferences.getString("pass", ""));
        if (check) {
            mData.child("Users").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                    Users us = dataSnapshot.getValue(Users.class);
//
//                    if (edtEmailDN.getText().toString().equals(us.getEmail()) && edtPassDN.getText().toString().equals(us.getPassword())) {
//                        String idDN = us.getId_Users();
//                        Intent intent = new Intent(DangNhapActivity.this, ListCuaHangActivity.class);
//                        intent.putExtra("idUser", idDN);
//                        startActivity(intent);
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
//                    }
                    if (rbGhiNho.isChecked()) {
                        CreateDataGhiNho(edtEmailDN.getText().toString(), edtPassDN.getText().toString(), true);
                    } else {
                        CreateDataGhiNho("", "", false);

                    }

                    Users us = dataSnapshot.getValue(Users.class);
                    arrUser.add(us);
                    for (int i = 0; i < arrUser.size(); i++){
                        if (arrUser.get(i).getEmail().equals(edtEmailDN.getText().toString()) && arrUser.get(i).getPassword().equals(edtPassDN.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                            String idDN = arrUser.get(i).getId_Users();
                            Intent intent = new Intent(DangNhapActivity.this, ListCuaHangActivity.class);
                            intent.putExtra("idUser", idDN);
                            startActivity(intent);
                            finish();
                            break;

                        } else {
                            Log.d("---", "ok1");
                            Toast.makeText(getApplicationContext(), "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    }
//
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

    }
}
