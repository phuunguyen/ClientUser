package com.example.clientuser.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.clientuser.R;
import com.example.clientuser.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {
    Context context;
    int layoutResource;
    ArrayList<Order> data = null;

    public OrderAdapter(Context context, int layoutResource, ArrayList<Order> data) {
        super(context, layoutResource, data);
        this.layoutResource = layoutResource;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        OrderHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);

            holder = new OrderHolder();
            holder.imgOrder = (ImageView) row.findViewById(R.id.imgOrder);
            holder.txtNgayTao = (TextView) row.findViewById(R.id.txtNgayTao);
            holder.txtMaDonHang = (TextView) row.findViewById(R.id.txtMaDonHang);

            row.setTag(holder);
        } else {
            holder = (OrderHolder) row.getTag();
        }

        Order item = data.get(position);
        holder.imgOrder.setImageResource(item.getImageOrder());
        holder.txtMaDonHang.setText(item.getTxtMaDonHang());
        holder.txtNgayTao.setText(item.getTxtNgayTao());

        return row;
    }

    static class OrderHolder {
        ImageView imgOrder;
        TextView txtMaDonHang, txtNgayTao;
    }
}
