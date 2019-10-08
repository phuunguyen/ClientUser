package com.example.clientuser.model;

import android.graphics.Bitmap;

public class CuaHang {
    private String imageCuaHang;
    private String shopName;
    private String shopAddress;
    private double rating;

    public CuaHang() {
    }

    public CuaHang(String imageCuaHang, String shopName, String shopAddress, double rating) {
        this.imageCuaHang = imageCuaHang;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.rating = rating;
    }

    public String getImageCuaHang() {
        return imageCuaHang;
    }

    public void setImageCuaHang(String imageCuaHang) {
        this.imageCuaHang = imageCuaHang;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
