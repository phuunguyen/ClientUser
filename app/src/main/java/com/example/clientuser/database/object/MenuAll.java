package com.example.clientuser.database.object;

public class MenuAll {
    public String idMenu;
    public String idStore;
    public String menuName;

    public MenuAll() {
    }

    public MenuAll(String idMenu, String idStore, String menuName) {
        this.idMenu = idMenu;
        this.idStore = idStore;
        this.menuName = menuName;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getIdStore() {
        return idStore;
    }

    public void setIdStore(String idStore) {
        this.idStore = idStore;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
