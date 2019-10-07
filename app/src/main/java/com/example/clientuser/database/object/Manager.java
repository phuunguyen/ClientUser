package com.example.clientuser.database.object;

public class Manager {
    String id_Manager;
    String id_Store;
    String username;
    String password;

    public Manager() {
    }

    public Manager(String id_Manager, String id_Store, String username, String password) {
        this.id_Manager = id_Manager;
        this.id_Store = id_Store;
        this.username = username;
        this.password = password;
    }

    public String getId_Manager() {
        return id_Manager;
    }

    public void setId_Manager(String id_Manager) {
        this.id_Manager = id_Manager;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
