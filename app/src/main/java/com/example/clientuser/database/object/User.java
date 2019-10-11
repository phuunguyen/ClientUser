package com.example.clientuser.database.object;

public class User {

    public String idUser;
    public String name;
    public String image;
    public String phone;
    public String email;
    public String passUser;

    public User() {
    }

    public User(String idUser, String name, String image, String phone, String email, String passUser) {
        this.idUser = idUser;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.passUser = passUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }
}
