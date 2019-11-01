package com.example.clientuser.model;

public class Order {
    private int imageOrder;
    private String txtMaDonHang;
    private String txtNgayTao;

    public Order() {
    }

    public Order(int imageOrder, String txtMaDonHang, String txtNgayTao) {
        this.imageOrder = imageOrder;
        this.txtMaDonHang = txtMaDonHang;
        this.txtNgayTao = txtNgayTao;
    }

    public int getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(int imageOrder) {
        this.imageOrder = imageOrder;
    }

    public String getTxtMaDonHang() {
        return txtMaDonHang;
    }

    public void setTxtMaDonHang(String txtMaDonHang) {
        this.txtMaDonHang = txtMaDonHang;
    }

    public String getTxtNgayTao() {
        return txtNgayTao;
    }

    public void setTxtNgayTao(String txtNgayTao) {
        this.txtNgayTao = txtNgayTao;
    }
}
