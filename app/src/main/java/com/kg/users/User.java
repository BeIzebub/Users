package com.kg.users;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

class User {

    private static int id = 0;

    @SerializedName("id")
    @Expose
    private int userId;

    @Expose
    private String name;

    @Expose
    private String lastName;

    @Expose
    private String email;

    @Expose(serialize = false)
    private String password;

    @Expose
    private Date date;

    @Expose
    private String imageUri;

    public User(String name, String lastName, String email, String password, Date date, String imageUri) {
        id++;
        userId = id;

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.date = date;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }

    public String getImageUri() {
        return imageUri;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", imageUri='" + imageUri + '\'' +
                '}';
    }
}
