package com.example.clientuser.model;

public class Cart {
    String idGioHang;
    String idUser;
    String idStore;
    String idProduct;
    String productImage;
    String productName;
    double productPrice;
    int quantity;

    public Cart() {
    }

    public Cart(String idGioHang, String idUser, String idStore, String idProduct, String productImage, String productName, double productPrice, int quantity) {
        this.idGioHang = idGioHang;
        this.idUser = idUser;
        this.idStore = idStore;
        this.idProduct = idProduct;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(String idGioHang) {
        this.idGioHang = idGioHang;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
