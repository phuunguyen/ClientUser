package com.example.clientuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientuser.R;
import com.example.clientuser.model.CuaHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CuaHangAdapter extends ArrayAdapter<CuaHang> {
    Context context;
    int layoutResource;
    ArrayList<CuaHang> data = null;

    public CuaHangAdapter(Context context, int layoutResource, ArrayList<CuaHang> data) {
        super(context, layoutResource, data);
        this.context = context;
        this.layoutResource = layoutResource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        CuaHangHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);

            holder = new CuaHangHolder();
            holder.imgCuaHang = (ImageView)row.findViewById(R.id.imgCuaHang);
            holder.txtShopName = (TextView)row.findViewById(R.id.txtShopName);
            holder.txtShopAddress = (TextView)row.findViewById(R.id.txtShopAddress);
            holder.txtRating = (TextView)row.findViewById(R.id.txtRating);

            row.setTag(holder);
        }else {
            holder = (CuaHangHolder) row.getTag();
        }

        CuaHang item = data.get(position);
        Picasso.get().load(item.getImageCuaHang()).into(holder.imgCuaHang);
        holder.txtRating.setText(item.getRating() + "");
        holder.txtShopAddress.setText(item.getShopAddress());
        holder.txtShopName.setText(item.getShopName());

        return row;
    }

    static class CuaHangHolder{
        ImageView imgCuaHang;
        TextView txtShopName;
        TextView txtShopAddress;
        TextView txtRating;
    }
}
