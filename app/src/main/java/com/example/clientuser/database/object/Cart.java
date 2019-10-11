package com.example.clientuser.database.object;

public class Cart {
    String id_DonHang;
    String id_User;
    String id_Store;
    String id_Product;


    int quantity;
    int price;
    int check;


    String delivery;
    String status;

    String txtMaDH, txtNgayTao, imgSP, imgStar;



    public Cart() {
    }

    public Cart(String id_DonHang, String txtMaDH, String txtNgayTao, String imgSP, String imgStar) {
        this.id_DonHang = id_DonHang;
        this.txtMaDH = txtMaDH;
        this.txtNgayTao = txtNgayTao;
        this.imgSP = imgSP;
        this.imgStar = imgStar;
    }

    public String getTxtMaDH() {
        return txtMaDH;
    }

    public void setTxtMaDH(String txtMaDH) {
        this.txtMaDH = txtMaDH;
    }

    public String getTxtNgayTao() {
        return txtNgayTao;
    }

    public void setTxtNgayTao(String txtNgayTao) {
        this.txtNgayTao = txtNgayTao;
    }

    public String getImgSP() {
        return imgSP;
    }

    public void setImgSP(String imgSP) {
        this.imgSP = imgSP;
    }

    public String getImgStar() {
        return imgStar;
    }

    public void setImgStar(String imgStar) {
        this.imgStar = imgStar;
    }

    public String getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(String id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public String getId_Product() {
        return id_Product;
    }

    public void setId_Product(String id_Product) {
        this.id_Product = id_Product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
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
}
