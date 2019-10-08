package com.example.clientuser.model;

public class Product {
    private String idProduct;
    private String imgProduct;
    private String nameProduct;
    private double priceProduct;

    public Product() {
    }

    public Product(String idProduct, String imgProduct, String nameProduct, double priceProduct) {
        this.idProduct = idProduct;
        this.imgProduct = imgProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
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
}
