package com.example.clientuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clientuser.database.object.Delivery;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mData;
    Button btnSave;
    EditText edtTime;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = database.getReference();
        setControl();
        setEvent();
        mData.child("Test").child("max").addValueEventListener(new ValueEventListener() {

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
    }

    private void setEvent() {
        final Delivery delivery = new Delivery();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                delivery.setId_DonHang("DH" + i);
                delivery.setId_Shipper("SP" + i);
                delivery.setTime_Delivery(edtTime.getText().toString());
                mData.child("Test").child("GH" + i).setValue(delivery);
                mData.child("Test").child("max").setValue(i);
            }
        });

    }

    private void setControl() {
        edtTime = (EditText) findViewById(R.id.edtTime);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

}
