package com.example.clientuser.database.object;

public class Manage {

    public String idManage;
    public String idStore;
    public String userName_Manager;
    public String passWord_Manager;

    public Manage() {
    }

    public Manage(String idManage, String idStore, String userName_Manager, String passWord_Manager) {
        this.idManage = idManage;
        this.idStore = idStore;
        this.userName_Manager = userName_Manager;
        this.passWord_Manager = passWord_Manager;
    }

    public String getIdManage() {
        return idManage;
    }

    public void setIdManage(String idManage) {
        this.idManage = idManage;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getUserName_Manager() {
        return userName_Manager;
    }

    public void setUserName_Manager(String userName_Manager) {
        this.userName_Manager = userName_Manager;
    }

    public String getPassWord_Manager() {
        return passWord_Manager;
    }

    public void setPassWord_Manager(String passWord_Manager) {
        this.passWord_Manager = passWord_Manager;
    }
}
