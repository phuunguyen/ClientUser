package com.example.clientuser;

public class CuaHang {
   private int imgLogoCH;
   private String tenCH;
   private String diaChi;
   private String soSao;
   private int imgSao;

    public CuaHang() {
    }

    public CuaHang(int imgLogoCH, String tenCH, String diaChi, String soSao, int imgSao) {
        this.imgLogoCH = imgLogoCH;
        this.tenCH = tenCH;
        this.diaChi = diaChi;
        this.soSao = soSao;
        this.imgSao = imgSao;
    }

    public int getImgLogoCH() {
        return imgLogoCH;
    }

    public void setImgLogoCH(int imgLogoCH) {
        this.imgLogoCH = imgLogoCH;
    }

    public String getTenCH() {
        return tenCH;
    }

    public void setTenCH(String tenCH) {
        this.tenCH = tenCH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoSao() {
        return soSao;
    }

    public void setSoSao(String soSao) {
        this.soSao = soSao;
    }

    public int getImgSao() {
        return imgSao;
    }

    public void setImgSao(int imgSao) {
        this.imgSao = imgSao;
    }
}
