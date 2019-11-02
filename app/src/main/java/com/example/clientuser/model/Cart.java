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
    String check;
    String delivery;
    String status;
    String finish;
    String txtNgayTao;
    String imgStar;

    public Cart() {
    }

    public Cart(String idGioHang, String idUser, String idStore, String idProduct, String productImage, String productName, double productPrice, int quantity, String check, String delivery, String status, String txtNgayTao, String imgStar, String finish) {
        this.idGioHang = idGioHang;
        this.idUser = idUser;
        this.idStore = idStore;
        this.idProduct = idProduct;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.check = check;
        this.delivery = delivery;
        this.status = status;
        this.txtNgayTao = txtNgayTao;
        this.imgStar = imgStar;
        this.finish = finish;
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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxtNgayTao() {
        return txtNgayTao;
    }

    public void setTxtNgayTao(String txtNgayTao) {
        this.txtNgayTao = txtNgayTao;
    }

    public String getImgStar() {
        return imgStar;
    }

    public void setImgStar(String imgStar) {
        this.imgStar = imgStar;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }
}
