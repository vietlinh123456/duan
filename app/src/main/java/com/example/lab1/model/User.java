package com.example.lab1.model;

public class User {
    private String ten;
    private String pass;
    private String phone;
    private String hoten;


    public User() {
    }

    public User(String ten, String pass, String phone, String hoten) {
        this.ten = ten;
        this.pass = pass;
        this.phone = phone;
        this.hoten = hoten;

    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }


}
