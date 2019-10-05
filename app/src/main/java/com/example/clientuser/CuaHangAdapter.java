package com.example.clientuser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CuaHangAdapter extends ArrayAdapter<CuaHang> {
    Context context = null;
    ArrayList<CuaHang> myArray = null;
    int layoutID;

    public CuaHangAdapter(Context context, int layoutID, ArrayList<CuaHang> arrayList) {
        super(context, layoutID, arrayList);
        this.context = (Activity) context;
        this.layoutID = layoutID;
        this.myArray = arrayList;

    }

    static class CuaHangHolder{
        ImageView imgIcon;
        ImageView imgStar;
        TextView txtName;
        TextView txtAddress;
        TextView numberStar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        CuaHangHolder cuaHangHolder= null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            row = inflater.inflate(layoutID,parent,false);

            cuaHangHolder = new CuaHangHolder();
            cuaHangHolder.imgIcon = (ImageView)row.findViewById(R.id.imgLogo);
            cuaHangHolder.txtName = (TextView)row.findViewById(R.id.txtTenCuaHang);
            cuaHangHolder.txtAddress = (TextView)row.findViewById(R.id.txtDiaChi);
            cuaHangHolder.numberStar = (TextView)row.findViewById(R.id.soSao);
            cuaHangHolder.imgStar = (ImageView)row.findViewById(R.id.imgSao);


            row.setTag(cuaHangHolder);
        } else cuaHangHolder = (CuaHangHolder) row.getTag();

        CuaHang item = myArray.get(position);
        cuaHangHolder.txtName.setText(item.getTenCH());
        cuaHangHolder.imgIcon.setImageResource(item.getImgLogoCH());
        cuaHangHolder.txtAddress.setText(item.getDiaChi());
        cuaHangHolder.numberStar.setText(item.getSoSao());
        cuaHangHolder.imgStar.setImageResource(item.getImgSao());

        return row;


    }
}
