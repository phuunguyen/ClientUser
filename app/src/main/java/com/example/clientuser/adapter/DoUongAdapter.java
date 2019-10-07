package com.example.clientuser.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clientuser.R;
import com.example.clientuser.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


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

    static class ProductHolder{
        ImageView imgIcon;
        TextView tvNameDoUong, Price;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductHolder productHolder = null;

        if (row == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();

            row = layoutInflater.inflate(layoutID,parent,false);

            productHolder = new ProductHolder();
            productHolder.imgIcon = (ImageView)row.findViewById(R.id.imgProduct);
            productHolder.tvNameDoUong = (TextView)row.findViewById(R.id.nameProduct);
            productHolder.Price = (TextView)row.findViewById(R.id.product_Price);

            row.setTag(productHolder);
        }else productHolder = (ProductHolder)row.getTag();

        Product item = arrProduct.get(position);
        Picasso.get().load(item.getImgProduct()).into(productHolder.imgIcon);
        productHolder.tvNameDoUong.setText(item.getNameProduct());
        productHolder.Price.setText(item.getPriceProduct() + "");

        return row;
    }
}
