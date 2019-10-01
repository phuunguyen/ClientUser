package com.example.clientuser.database.object;

public class Menu {
    String id_Menu;
    String id_Store;
    String menu_Name;

    public Menu() {
    }

    public Menu(String id_Menu, String id_Store, String menu_Name) {
        this.id_Menu = id_Menu;
        this.id_Store = id_Store;
        this.menu_Name = menu_Name;
    }

    public String getId_Menu() {
        return id_Menu;
    }

    public void setId_Menu(String id_Menu) {
        this.id_Menu = id_Menu;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public String getMenu_Name() {
        return menu_Name;
    }

    public void setMenu_Name(String menu_Name) {
        this.menu_Name = menu_Name;
    }
}
