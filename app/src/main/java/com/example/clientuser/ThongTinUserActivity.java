package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientuser.database.object.Users;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

public class ThongTinUserActivity extends AppCompatActivity {

    TextView txtName, txtEmail, txtAdress, txtPhone;
    TextInputEditText edtName, edtEmail, edtAdress, edtPhone;
    Button btnDX;
    ImageView imgAvt, imgVEdit, imgVSave, imgBack;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    DatabaseReference Table_User = mData.child("Users");
    Users users = new Users();
    int REQUEST_CHOOSE_PHOTO = 1;
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_user);
        setEvent();
        showUsers();

    }

    public void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            try {
                Uri imgeUri = data.getData();
                InputStream is = getContentResolver().openInputStream(imgeUri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                imgAvt.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public void showUsers() {

        //saveUsers = new SaveUsers(getApplicationContext());
        // Hien thong tin user

        //Log.d("AA1", idUser);
        final SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDUSER", Context.MODE_PRIVATE);
        final String idUser = sharedPreferences.getString("IDUSER", null);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        Table_User.child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    //Log.d("AAA", dataSnapshot.child("address").getValue().toString());
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                    txtAdress.setText(dataSnapshot.child("address").getValue().toString());
                    txtPhone.setText(dataSnapshot.child("phone").getValue().toString());
                }
                if (dataSnapshot.child("image").getValue() == null) {
                    //Toast.makeText(ThongTinUserActivity.this, "Khong co du lieu", Toast.LENGTH_SHORT).show();
                } else {
                    Picasso.get().load(dataSnapshot.child("image").getValue().toString()).into(imgAvt);
                    //imgAvt.setImageBitmap(dataSnapshot.child("image").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        imgVEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"ád", Toast.LENGTH_SHORT).show();
                imgVEdit.setVisibility(View.GONE);
                imgVSave.setVisibility(View.VISIBLE);
                txtName.setVisibility(View.GONE);
                txtEmail.setVisibility(View.VISIBLE);
                txtAdress.setVisibility(View.GONE);
                txtPhone.setVisibility(View.GONE);
                edtName.setVisibility(View.VISIBLE);
                //edtEmail.setVisibility(View.VISIBLE);
                edtAdress.setVisibility(View.VISIBLE);
                edtPhone.setVisibility(View.VISIBLE);
                //btnUL.setVisibility(View.VISIBLE);

                Table_User.child(idUser).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        edtName.setText(dataSnapshot.child("name").getValue().toString());
                        //edtEmail.setText(dataSnapshot.child("email").getValue().toString());
                        edtAdress.setText(dataSnapshot.child("address").getValue().toString());
                        edtPhone.setText(dataSnapshot.child("phone").getValue().toString());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                imgAvt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        choosePhoto();
                    }
                });


            }
        });

        imgVSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgVSave.setVisibility(View.GONE);

                mData.child("Users").child(idUser).child("name").setValue(edtName.getText().toString());
                mData.child("Users").child(idUser).child("address").setValue(edtAdress.getText().toString());
                mData.child("Users").child(idUser).child("phone").setValue(Integer.parseInt(edtPhone.getText().toString()));

                //mData.child("Users").child(idUser).child("image").setValue()

                //Calendar calendar = Calendar.getInstance();
                StorageReference mountainsRef = mStorageRef.child(idUser + ".png");
                imgAvt.setDrawingCacheEnabled(true);
                imgAvt.buildDrawingCache();
                //Log.d("AAA", imgAvt.getDrawable().getClass().toString());
                if (imgAvt.getDrawable().getClass().equals(BitmapDrawable.class)) {
                    Bitmap bitmap = ((BitmapDrawable) imgAvt.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] data = baos.toByteArray();
                    UploadTask uploadTask = mountainsRef.putBytes(data);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoLink = uri.toString();
                                    mData.child("Users").child(idUser).child("image").setValue(photoLink);
                                }
                            });

                        }
                    });
                }


                imgVEdit.setVisibility(View.VISIBLE);
                txtName.setVisibility(View.VISIBLE);
                txtEmail.setVisibility(View.VISIBLE);
                txtAdress.setVisibility(View.VISIBLE);
                txtPhone.setVisibility(View.VISIBLE);
                edtName.setVisibility(View.GONE);
                //edtEmail.setVisibility(View.GONE);
                edtAdress.setVisibility(View.GONE);
                edtPhone.setVisibility(View.GONE);
                //btnUL.setVisibility(View.GONE);


            }
        });
        btnDX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveUsers = new SaveUsers(getApplicationContext());
                editor.clear();
                editor.commit();
                Intent intent1 = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void setEvent() {
        txtName = (TextView) findViewById(R.id.tvUserName);
        txtEmail = (TextView) findViewById(R.id.tvEmail);
        txtAdress = (TextView) findViewById(R.id.tvAdress);
        txtPhone = (TextView) findViewById(R.id.tvPhone);
        btnDX = (Button) findViewById(R.id.btnLogout);
        //btnUL   = (Button) findViewById(R.id.btnUpload);
        imgAvt = (ImageView) findViewById(R.id.imgProfile);
        imgVEdit = (ImageView) findViewById(R.id.imgVEdit);
        imgVSave = (ImageView) findViewById(R.id.imgVSave);
        edtName = (TextInputEditText) findViewById(R.id.edtNameUser);
        //edtEmail = (EditText) findViewById(R.id.edtEmailUser);
        edtAdress = (TextInputEditText) findViewById(R.id.edtAddressUser);
        edtPhone = (TextInputEditText) findViewById(R.id.edtPhoneUser);
        imgBack = (ImageView) findViewById(R.id.imgButtonLeft);
    }
}
