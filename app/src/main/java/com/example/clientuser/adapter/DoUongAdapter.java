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


public class DoUongAdapter extends ArrayAdapter<Product> {
    Context context = null;
    ArrayList<Product> arrProduct = null;
    int layoutID;


    public DoUongAdapter(Context context, int layoutID, ArrayList<Product> arrProduct) {
        super(context, layoutID, arrProduct);
        this.context = context;
        this.arrProduct = arrProduct;
        this.layoutID = layoutID;
    }

    static class ProductHolder {
        ImageView imgIcon;
        TextView tvNameDoUong, Price;
        Button btnAdd;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductHolder productHolder = null;

        if (row == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();

            row = layoutInflater.inflate(layoutID, parent, false);

            productHolder = new ProductHolder();
            productHolder.imgIcon = (ImageView) row.findViewById(R.id.imgProduct);
            productHolder.tvNameDoUong = (TextView) row.findViewById(R.id.nameProduct);
            productHolder.Price = (TextView) row.findViewById(R.id.product_Price);
            productHolder.btnAdd = (Button) row.findViewById(R.id.addProducts);
            productHolder.btnAdd.setOnClickListener(ButtonClickListener);

            row.setTag(productHolder);
        } else {
            productHolder = (ProductHolder) row.getTag();
        }
        productHolder.btnAdd.setTag(position);

        Product item = arrProduct.get(position);
        Picasso.get().load(item.getImgProduct()).into(productHolder.imgIcon);
        productHolder.tvNameDoUong.setText(item.getNameProduct());
        productHolder.Price.setText(item.getPriceProduct() + "");

        return row;
    }

    ArrayList<String> listProduct = new ArrayList<>();
    private View.OnClickListener ButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            String idProduct = arrProduct.get(position).getIdProduct();
            listProduct.add(idProduct);
            Set<String> set = new LinkedHashSet<>(listProduct);
            ArrayList<String> listProductWithoutDuplicate = new ArrayList<>(set);
            saveArrayList(listProductWithoutDuplicate, "listProduct");
            Toast.makeText(context, "Thêm " + arrProduct.get(position).getNameProduct() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    };

    public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }
}
