package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.clientuser.adapter.TrangThaiAdapter;
import com.example.clientuser.database.object.Rating;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DanhGiaActivity extends AppCompatActivity {
    EditText txtComment;
    Button btnSubmit;
    RatingBar ratingBar;
    ImageButton btnBack;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    Rating rating = new Rating();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(DanhGiaActivity.this, TrangThaiActivity.class);
                startActivity(intent);
            }
        });
        mData.child("MaxID").child("MaxID_Comments").addValueEventListener(new ValueEventListener() {
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmt = txtComment.getText().toString().trim();
                float start = ratingBar.getRating();

                i++;
                //rating.setComment(txtComment.getText().toString());
                //rating.setRating(ratingBar.getRating());
                SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFERENCES_IDSTORE", Context.MODE_PRIVATE);
                String idStore = sharedPreferences.getString("IDSTORE", "");
                //Lua id Name
                SharedPreferences sharedPreferences1 = getSharedPreferences("SHARED_PREFERENCES_IDUSER", Context.MODE_PRIVATE);
                final String idUser = sharedPreferences1.getString("IDUSER", "");
                final String idName = sharedPreferences1.getString("IDName", "");
                rating.setComment(txtComment.getText().toString());
                rating.setRating(ratingBar.getRating());
                rating.setId_User(idUser);
                rating.setId_Name(idName);
                mData.child("MaxID").child("MaxID_Comments").setValue(i);
                mData.child("Comment").child(idStore).child("Comment" + i).setValue(rating);
                Toast.makeText(getApplicationContext(), "Đánh giá thành công!!!", Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(DanhGiaActivity.this, TrangThaiActivity.class);
                startActivity(intent);
            }
        });




    }

    private void setControl() {
        txtComment = findViewById(R.id.txtCmt);
        btnSubmit = findViewById(R.id.btnGui);
        ratingBar = findViewById(R.id.ratingDG);
        btnBack = findViewById(R.id.imgButtonLeft);
    }
}
