package com.example.clientuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListCuaHangActivity extends AppCompatActivity {

    private ListView listViewDrink;
    ArrayList<CuaHang> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cua_hang);
        setControl();
        setEvent();
    }
    public void setControl() {
        listViewDrink = (ListView) findViewById(R.id.lvDSCH);
    }

    public void setEvent() {
        khoiTao();
        CuaHangAdapter adapter = new CuaHangAdapter(this, R.layout.item_dsch, data);
        listViewDrink.setAdapter(adapter);

        listViewDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });

    }

    public void khoiTao() {
        data.add(new CuaHang(R.drawable.logoapp,"GongCha","53 Vo Van Ngan","5",R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp,"GongCha","53 Vo Van Ngan","5",R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp,"GongCha","53 Vo Van Ngan","5",R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp,"GongCha","53 Vo Van Ngan","5",R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp,"GongCha","53 Vo Van Ngan","5",R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp,"GongCha","53 Vo Van Ngan","5",R.drawable.ic_star));

    }
}
