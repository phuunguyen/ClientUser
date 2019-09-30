package com.example.clientuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.clientuser.database.create.CreateDatabase;
import com.example.clientuser.database.object.Delivery;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    CreateDatabase cd = new CreateDatabase();
    DatabaseReference mData;
    Button btnSave;
    EditText edtTime;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = cd.database.getReference();
        setControl();
        setEvent();
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
            }
        });

    }

    private void setControl() {
        edtTime = (EditText)findViewById(R.id.edtTime);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

}
