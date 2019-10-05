package com.example.clientuser;

import android.os.Bundle;
import android.widget.ListView;

import com.example.clientuser.Adapter.DoUongAdapter;
import com.example.clientuser.model.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.clientuser.database.object.Product;

public class ListDoUongActivity extends AppCompatActivity {

    private ListView lvDoUong;
    ArrayList<Product> data = new ArrayList<>();
    DoUongAdapter adapter = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_do_uong);


        //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //lvDoUong.setAdapter(arrayAdapter);

        databaseReference = database.getReference();
        setControl();
        setEvent();
    }
    public void setControl(){
        lvDoUong = (ListView) findViewById(R.id.productList);
    }

    public void setEvent(){
        adapter = new DoUongAdapter(this, R.layout.listview_douong,data);
        lvDoUong.setAdapter(adapter);
        loadData();
    }

    public void loadData(){
        databaseReference.child("Product").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = new Product();
                product.setImgProduct(dataSnapshot.child("Product_image").getValue().toString());
                product.setNameProduct(dataSnapshot.child("Product_name").getValue().toString());
                product.setPriceProduct(Double.parseDouble(dataSnapshot.child("Price").getValue().toString()));
                data.add(product);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
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
