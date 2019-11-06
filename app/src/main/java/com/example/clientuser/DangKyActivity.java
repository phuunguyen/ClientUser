package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity {

    TextView txtNhapDeDN;
    TextInputEditText edtName, edtPhone, edtEmail, edtAddress, edtPass, edtPass1;
    MaterialButton btnDK;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    DatabaseReference Table_User = mData.child("Users");
    Users users = new Users();
    ArrayList<Users> arrUser = new ArrayList();
    int i;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setControl();
        setEvent();


    }

    public void setEvent() {
        loadUser();
        mData.child("MaxID").child("MaxID_Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
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

                final String email = edtEmail.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String adress = edtAddress.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String pass1 = edtPass1.getText().toString().trim();

                if (!isEmptyOrNull(name)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isEmptyOrNull(adress)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmailID(email)) {
                    Toast.makeText(getApplicationContext(), "Email không đúng định dạng!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidPassword(pass)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu của bạn phải từ 6 kí tự trở lên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidPassword(pass1)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại của bạn phải từ 6 kí tự trở lên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidPhoneNumber(phone)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra lại số điện thoại của bạn!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(checkEmailExist(email)){
                    i++;
                    dangKi(i);
                    Log.d("+++", email);
                }



//                mData.child("Users").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        loadUser();
//                        for (DataSnapshot ds : dataSnapshot.getChildren())
//                            if (ds.child("email").getValue().toString().equals(email)) {
//                                Toast.makeText(DangKyActivity.this, "Email nay da ton tai!!", Toast.LENGTH_SHORT).show();
//                                break;
//                            }else{
//                                Toast.makeText(DangKyActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
//                                break;
//                            }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

            }
        });
        txtNhapDeDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private boolean checkEmailExist(String email) {
        for (int j = 0; j < arrUser.size(); j++) {
            if (arrUser.get(j).getEmail().equals(email)) {
                Log.d("*-*", arrUser.get(j).getEmail());
                Toast.makeText(DangKyActivity.this, "Email nay da ton tai!!", Toast.LENGTH_SHORT).show();
                return false;
           }
        }
        return true;
    }

    private void dangKi(int i) {
        users.setName(edtName.getText().toString());
        users.setPhone(Integer.parseInt(edtPhone.getText().toString()));
        users.setEmail(edtEmail.getText().toString());
        users.setAddress(edtAddress.getText().toString());
        users.setPassword(edtPass.getText().toString());
        users.setId_Users("User" + i);
        if (edtPass.getText().toString().equals(edtPass1.getText().toString())) {
            Table_User.child("User" + i).setValue(users);
            Toast.makeText(getApplicationContext(), "Dang ki thanh cong", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Sai mat khau vui long nhap lai", Toast.LENGTH_SHORT).show();
        }
        mData.child("MaxID").child("MaxID_Users").setValue(i);
    }

    public void setControl() {
        txtNhapDeDN = (TextView) findViewById(R.id.txtNhapDeDN);
        btnDK = (MaterialButton) findViewById(R.id.btnDangKy);
        edtName = (TextInputEditText) findViewById(R.id.edtName);
        edtEmail = (TextInputEditText) findViewById(R.id.edtEmail);
        edtAddress = (TextInputEditText) findViewById(R.id.edtAddress);
        edtPhone = (TextInputEditText) findViewById(R.id.edtPhone);
        edtPass = (TextInputEditText) findViewById(R.id.edtPassWord);
        edtPass1 = (TextInputEditText) findViewById(R.id.edtPassWord1);
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

    private boolean isEmptyOrNull(String text) {
        if (text != null && !TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
    }

    private boolean isValidEmailID(String email) {
        String PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (password != null && password.length() >= 6) {
            return true;
        }
        return false;
    }

    private boolean isValidPhoneNumber(String phone) {
        if (phone != null && (phone.length() >= 6 || phone.length() < 13)) {
            return PhoneNumberUtils.isGlobalPhoneNumber(phone);
        }
        return false;
    }
}
