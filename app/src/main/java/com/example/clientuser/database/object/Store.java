package com.example.clientuser.database.object;

public class Store {
    String id_Store;
    String store_Name;
    String address;
    int phone;
    String userName;
    String password;
    String image;

    public Store() {
    }

    public Store(String id_Store, String store_Name, String address, int phone, String userName, String password, String image) {
        this.id_Store = id_Store;
        this.store_Name = store_Name;
        this.address = address;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.image = image;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public String getStore_Name() {
        return store_Name;
    }

    public void setStore_Name(String store_Name) {
        this.store_Name = store_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
