package com.example.clientuser.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.clientuser.R;
import com.example.clientuser.adapter.DoUongAdapter;
import com.example.clientuser.model.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class BubbleTeaFragment extends Fragment {
    private ListView lvDoUong;
    ArrayList<Product> data = new ArrayList<>();
    DoUongAdapter adapter = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_all_product, container, false);
        lvDoUong = (ListView) row.findViewById(R.id.productList);
        return row;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new DoUongAdapter(getContext(), R.layout.listview_item_douong, data);
        lvDoUong.setAdapter(adapter);
    }

    public void loadData() {
        final Intent intent = getActivity().getIntent();
        final String idStore = intent.getStringExtra("idStore");
        databaseReference.child("Product").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = new Product();
                if (idStore.equals(dataSnapshot.child("id_store").getValue().toString()) && dataSnapshot.child("id_menu").getValue().equals("002")) {
                    product.setIdProduct(dataSnapshot.child("id_product").getValue().toString());
                    product.setImgProduct(dataSnapshot.child("product_image").getValue().toString());
                    product.setNameProduct(dataSnapshot.child("product_name").getValue().toString());
                    product.setPriceProduct(Double.parseDouble(dataSnapshot.child("price").getValue().toString()));
                    data.add(product);
                }
                adapter.notifyDataSetChanged();
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
    public void onResume() {
        super.onResume();
        data.clear();
        loadData();
    }
}
