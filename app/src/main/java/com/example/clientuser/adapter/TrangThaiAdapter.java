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
import com.example.clientuser.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrangThaiAdapter extends ArrayAdapter<Cart> {
    Context context;
    int layoutID;
    ArrayList<Cart> data = null;

    public TrangThaiAdapter(Context context, int layoutID, ArrayList<Cart> data) {
        super(context, layoutID, data);
        this.context = context;
        this.layoutID = layoutID;
        this.data = data;
    }

    static class TrangThaiHolder{
        TextView txtMaDH, txtNgayTao;
        ImageView imgRating, imgSP;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        TrangThaiHolder trangThaiHolder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            row = inflater.inflate(layoutID,parent, false);

            trangThaiHolder = new TrangThaiHolder();
            trangThaiHolder.txtMaDH = row.findViewById(R.id.txtMaDonHang);
            trangThaiHolder.txtNgayTao = row.findViewById(R.id.txtNgayTao);
            trangThaiHolder.imgRating = row.findViewById(R.id.imgRating);
            trangThaiHolder.imgSP = row.findViewById(R.id.imgSP);

            row.setTag(trangThaiHolder);

        }
        else {
            trangThaiHolder = (TrangThaiHolder) row.getTag();
        }

        Cart item = data.get(position);
        Picasso.get().load(item.getProductImage()).into(trangThaiHolder.imgSP);
        trangThaiHolder.txtMaDH.setText(item.getIdGioHang());
        trangThaiHolder.txtNgayTao.setText(item.getTxtNgayTao());
        Picasso.get().load(item.getImgStar()).into(trangThaiHolder.imgRating);

        return  row;
    }
}
