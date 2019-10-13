package com.example.clientuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.clientuser.R;
import com.example.clientuser.model.Cart;
import com.example.clientuser.model.CuaHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Cart> {
    Context context;
    int layoutResource;
    ArrayList<Cart> data = null;

    public CartAdapter(Context context, int layoutResource, ArrayList<Cart> data) {
        super(context, layoutResource, data);
        this.context = context;
        this.layoutResource = layoutResource;
        this.data = data;
    }

    static class CartHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView quantity;
        Button btnDecQuantity;
        Button btnIncQuantity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        CartHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);

            holder = new CartHolder();
            holder.productImage = (ImageView) row.findViewById(R.id.imgProduct);
            holder.productName = (TextView) row.findViewById(R.id.productName);
            holder.productPrice = (TextView) row.findViewById(R.id.productPrice);
            holder.quantity = (TextView) row.findViewById(R.id.quantity);
            holder.btnDecQuantity = (Button)row.findViewById(R.id.btnDecQuantity);
            holder.btnIncQuantity = (Button)row.findViewById(R.id.btnDecQuantity);


            row.setTag(holder);
        } else {
            holder = (CartHolder) row.getTag();
        }

        holder.btnIncQuantity.setTag(position);
        holder.btnDecQuantity.setTag(position);

        Cart item = data.get(position);
        Picasso.get().load(item.getProductImage()).into(holder.productImage);
        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(item.getProductPrice() + "");
        holder.quantity.setText(item.getQuantity() + "");

        return row;
    }


}
