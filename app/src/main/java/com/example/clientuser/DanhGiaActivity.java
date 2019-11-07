package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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
    DatabaseReference Table_Comment = mData.child("Comment");
    DatabaseReference Table_Store = mData.child("Store");
    SharedPreferences sharedPreferences;
    String idLogin = "";
    Rating rating = new Rating();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        setControl();
        setEvent();
        ratingStore();
    }

    private void setEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
                if(!checkCmt(cmt)){
                    Toast.makeText(DanhGiaActivity.this, "Vui lòng nhập đánh giá của bạn!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!checkRating(start)){
                    Toast.makeText(DanhGiaActivity.this, "Vui lòng chọn số sao bạn muốn đánh giá!!", Toast.LENGTH_SHORT).show();
                    return;
                }

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
                onBackPressed();
            }
        });
    }

    public void ratingStore() {
        Table_Comment.child(idLogin).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double total = 0.0;
                double count = 0.0;
                double average = 0.0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    double rating = Double.parseDouble(ds.child("rating").getValue().toString());
                    total = total + rating;
                    count = count + 1;
                    average = total / count;
                }
                String s = String.valueOf(average);
                Log.d("---", s);
                //ratingBar.setRating(Float.parseFloat(String.valueOf(average[0])));
                Table_Store.child(idLogin).child("rating").setValue(average);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private boolean checkCmt(String cmt){
        if(cmt != null && !TextUtils.isEmpty(cmt))
            return true;
        return false;
    }

    private boolean checkRating(float rating){
        if(rating != 0)
            return true;
        return false;
    }

    private void setControl() {
        txtComment = findViewById(R.id.txtCmt);
        btnSubmit = findViewById(R.id.btnGui);
        ratingBar = findViewById(R.id.ratingDG);
        btnBack = findViewById(R.id.imgButtonLeft);
        sharedPreferences = getApplicationContext().getSharedPreferences("SHARED_PREFERENCES_IDSTORE",
                Context.MODE_PRIVATE);
        idLogin = sharedPreferences.getString("IDSTORE", "");
    }
}
