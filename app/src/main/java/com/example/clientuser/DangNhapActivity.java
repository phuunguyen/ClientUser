package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.database.object.Users;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangNhapActivity extends AppCompatActivity {
    MaterialButton btnDangNhap;
    TextView txtNDDK;
    TextInputEditText edtEmailDN, edtPassDN;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ArrayList<Users> arrUser = new ArrayList();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dang_nhap);
        setControl();
        setEvent();

    }

    private void setControl() {
        btnDangNhap = (MaterialButton) findViewById(R.id.btnDangNhap);
        txtNDDK = (TextView) findViewById(R.id.txtNhapDeDK);
        edtEmailDN = (TextInputEditText) findViewById(R.id.edtEmailDN);
        edtPassDN = (TextInputEditText) findViewById(R.id.edtPassWordDN);
    }

    private boolean isValidEmailID(String email) {
        String PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (password != null && password.length() < 6) {
            return true;
        }
        return false;
    }

    private void setEvent() {

        loadUser();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for (int i = 0; i < arrUser.size(); i++) {
                    if (arrUser.get(i).getEmail().equals(edtEmailDN.getText().toString()) && arrUser.get(i).getPassword().equals(edtPassDN.getText().toString())) {
                        //saveUsers.setLogin(true);
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        String idDN = arrUser.get(i).getId_Users();
                        String idName = arrUser.get(i).getName();
                        Intent intent = new Intent(DangNhapActivity.this, ListCuaHangActivity.class);
                        final SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDUSER", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("IDUSER", idDN).apply();
                        editor.putString("IDName", idName).apply();
                        editor.commit();
                        startActivity(intent);
                        finish();
                        break;

                    } else {
                        Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
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


    private void loadUser() {
        mData.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Users us = dataSnapshot.getValue(Users.class);
                arrUser.add(us);

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
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDUSER", Context.MODE_PRIVATE);
        String idUser = sharedPreferences.getString("IDUSER", "");
        if (!idUser.isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), ListCuaHangActivity.class);
            startActivity(intent);

        }
    }
}
