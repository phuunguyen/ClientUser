package com.example.clientuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientuser.R;
import com.example.clientuser.model.Cart;
import com.example.clientuser.model.CuaHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    Context context;
    ArrayList<Cart> data = new ArrayList<>();

    public CartAdapter(Context context, ArrayList<Cart> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listview_item_giohang, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartHolder holder, final int position) {
        Picasso.get().load(data.get(position).getProductImage()).into(holder.productImage);
        holder.productName.setText(data.get(position).getProductName());
        holder.productPrice.setText(data.get(position).getProductPrice() + " VND");
        holder.quantity.setText("SL: " + data.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class CartHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView quantity;


        public CartHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.imgProduct);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            quantity = (TextView) itemView.findViewById(R.id.edt_quantity);

        }
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Cart item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<Cart> getData() {
        return data;
    }
}
