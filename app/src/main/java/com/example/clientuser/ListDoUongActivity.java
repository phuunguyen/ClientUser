package com.example.clientuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clientuser.Adapter.DoUongAdapter;
import com.example.clientuser.database.object.Product;

import java.util.ArrayList;

public class ListDoUongActivity extends AppCompatActivity {

    private ListView lvDoUong;
    private TextView price, tenCH;
    private ImageView img;
    ArrayList<Product> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String TAG = "FIREBASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_do_uong);


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvDoUong = (ListView) findViewById(R.id.productList);
        //lvDoUong.setAdapter(arrayAdapter);
        setControl();
        setEvent();
    }
    public void setControl(){
        price = (TextView)findViewById(R.id.price);
        tenCH = (TextView)findViewById(R.id.nameProduct);
        img = (ImageView)findViewById(R.id.imgProduct);
        lvDoUong = (ListView) findViewById(R.id.productList);
    }

    public void setEvent(){
        khoiTao();
        DoUongAdapter adapter = new DoUongAdapter(this, R.layout.listview_douong,arrayList);
        lvDoUong.setAdapter(adapter);
    }

    public void khoiTao() {
        arrayList.add(new Product(R.drawable.logoapp , "trà sữa siêu ngon", 25000));
        arrayList.add(new Product(R.drawable.logoapp , "trà sữa siêu ngon", 25000));


    }
}
