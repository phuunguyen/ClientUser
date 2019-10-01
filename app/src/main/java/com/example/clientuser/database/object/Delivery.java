package com.example.clientuser.database.object;

import android.text.Editable;

public class Delivery {
    String id_DonHang;
    String id_Shipper;
    String time_Delivery;

    public Delivery() {
    }

    public Delivery(String id_DonHang, String id_Shipper, String time_Delivery) {
        this.id_DonHang = id_DonHang;
        this.id_Shipper = id_Shipper;
        this.time_Delivery = time_Delivery;
    }

    public String getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(String id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public String getId_Shipper() {
        return id_Shipper;
    }

    public void setId_Shipper(String id_Shipper) {
        this.id_Shipper = id_Shipper;
    }

    public String getTime_Delivery() {
        return time_Delivery;
    }

    public void setTime_Delivery(String time_Delivery) {
        this.time_Delivery = time_Delivery;
    }
}
