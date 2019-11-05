package com.example.clientuser.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductFragment extends Fragment {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView lvDoUong;
    ArrayList<Product> data = new ArrayList<>();
    DoUongAdapter adapter = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_all_product, container, false);
        lvDoUong = (RecyclerView) row.findViewById(R.id.productList);
        swipeContainer = (SwipeRefreshLayout)row.findViewById(R.id.swipeContainer);
        return row;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new DoUongAdapter(getContext(), data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        lvDoUong.setLayoutManager(layoutManager);
        lvDoUong.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFERENCES_IDSTORE", Context.MODE_PRIVATE);
        final String idStore = sharedPreferences.getString("IDSTORE", null);
        adapter.clear();
        databaseReference.child("Product").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = new Product();
                if (idStore.equals(dataSnapshot.child("id_store").getValue().toString())) {
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
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                // Stop animation (This will be after 3 seconds)
                swipeContainer.setRefreshing(false);
            }
        }, 4000); // Delay in millis
    }

    @Override
    public void onResume() {
        super.onResume();
        data.clear();
        loadData();
    }
}
