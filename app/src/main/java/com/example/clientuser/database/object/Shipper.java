package com.example.clientuser.database.object;

public class Shipper {
    String id_Shipper;
    String shipper_Name;
    int phone;
    String status;

    public Shipper() {
    }

    public Shipper(String id_Shipper, String shipper_Name, int phone, String status) {
        this.id_Shipper = id_Shipper;
        this.shipper_Name = shipper_Name;
        this.phone = phone;
        this.status = status;
    }

    public String getId_Shipper() {
        return id_Shipper;
    }

    public void setId_Shipper(String id_Shipper) {
        this.id_Shipper = id_Shipper;
    }

    public String getShipper_Name() {
        return shipper_Name;
    }

    public void setShipper_Name(String shipper_Name) {
        this.shipper_Name = shipper_Name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
