package com.example.clientuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ListCuaHangActivity extends AppCompatActivity {

    private ListView listViewDrink;
    private ImageButton btnCart, btnUser;
    ArrayList<CuaHang> data = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String TAG = "FIREBASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cua_hang);
        setControl();
        setEvent();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewDrink = findViewById(R.id.lvDSCH);
        //listViewDrink.setAdapter(adapter);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//        DatabaseReference myRef = database.getReference("Store");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                adapter.clear();
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    String key = data.getKey();
//                    String value = data.getValue().toString();
//                    adapter.add(key + "\n" + value);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//        });
    }

    public void setControl() {
        listViewDrink = (ListView) findViewById(R.id.lvDSCH);
        btnCart = (ImageButton) findViewById(R.id.btnCart);
        btnUser = (ImageButton) findViewById(R.id.btnUser);
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

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListCuaHangActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListCuaHangActivity.this, ThongTinUserActivity.class);
                startActivity(intent);
            }
        });
    }

    public void khoiTao() {
        data.add(new CuaHang(R.drawable.logoapp, "GongCha", "53 Vo Van Ngan", "5", R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp, "GongCha", "53 Vo Van Ngan", "5", R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp, "GongCha", "53 Vo Van Ngan", "5", R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp, "GongCha", "53 Vo Van Ngan", "5", R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp, "GongCha", "53 Vo Van Ngan", "5", R.drawable.ic_star));
        data.add(new CuaHang(R.drawable.logoapp, "GongCha", "53 Vo Van Ngan", "5", R.drawable.ic_star));

    }


}
