package com.example.clientuser.model;

public class Product {
    private String imgProduct;
    private String nameProduct;
    private double priceProduct;

    public Product(String imgProduct, String nameProduct, double priceProduct) {
        this.imgProduct = imgProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }


    public Product(){}


}
