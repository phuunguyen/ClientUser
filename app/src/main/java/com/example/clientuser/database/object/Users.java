package com.example.clientuser.database.object;

public class Users {
    String id_Users;
    String name;
    String image;
    String email;
    int phone;
    String password;

    public Users() {
    }

    public Users(String id_Users, String name, String image, String email, int phone, String password) {
        this.id_Users = id_Users;
        this.name = name;
        this.image = image;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getId_Users() {
        return id_Users;
    }

    public void setId_Users(String id_Users) {
        this.id_Users = id_Users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
