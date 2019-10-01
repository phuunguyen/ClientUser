package com.example.clientuser.database.object;

public class Product {
    String id_Product;
    String image;
    String product_Name;
    String id_Store;
    String id_Menu;
    int price;

    public Product() {
    }

    public Product(String id_Product, String image, String product_Name, String id_Store, String id_Menu, int price) {
        this.id_Product = id_Product;
        this.image = image;
        this.product_Name = product_Name;
        this.id_Store = id_Store;
        this.id_Menu = id_Menu;
        this.price = price;
    }

    public String getId_Product() {
        return id_Product;
    }

    public void setId_Product(String id_Product) {
        this.id_Product = id_Product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public String getId_Menu() {
        return id_Menu;
    }

    public void setId_Menu(String id_Menu) {
        this.id_Menu = id_Menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
