package com.example.clientuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientuser.GioHangActivity;
import com.example.clientuser.R;
import com.example.clientuser.model.Product;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class DoUongAdapter extends RecyclerView.Adapter<DoUongAdapter.ProductHolder> {
    Context context = null;
    ArrayList<Product> arrProduct = new ArrayList<>();
    static ArrayList<String> listProduct = new ArrayList<>();

    public DoUongAdapter(Context context, ArrayList<Product> arrProduct) {
        this.context = context;
        this.arrProduct = arrProduct;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_item_douong, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, final int position) {
        holder.Price.setText((int) arrProduct.get(position).getPriceProduct() + " VND");
        holder.tvNameDoUong.setText(arrProduct.get(position).getNameProduct());
        Picasso.get().load(arrProduct.get(position).getImgProduct()).into(holder.imgIcon);
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idProduct = arrProduct.get(position).getIdProduct();
                listProduct.add(idProduct);
                saveArrayList(listProduct, "listProduct");
                Toast.makeText(context, "Thêm " + arrProduct.get(position).getNameProduct() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }


    public class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView tvNameDoUong, Price;
        Button btnAdd;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgProduct);
            tvNameDoUong = (TextView) itemView.findViewById(R.id.nameProduct);
            Price = (TextView) itemView.findViewById(R.id.product_Price);
            btnAdd = (Button) itemView.findViewById(R.id.addProducts);
        }
    }


    public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = context.getSharedPreferences("SHARED_PREFERENCES_LISTPRODUCT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }
}
