package com.example.clientuser.database.object;

public class Rating {
    String id_Store;
    String id_User;
    String comment;
    int rating;

    public Rating() {
    }

    public Rating(String id_Store, String id_User, String comment, int rating) {
        this.id_Store = id_Store;
        this.id_User = id_User;
        this.comment = comment;
        this.rating = rating;
    }

    public String getId_Store() {
        return id_Store;
    }

    public void setId_Store(String id_Store) {
        this.id_Store = id_Store;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
