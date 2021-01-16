package com.example.lab1.model;

import java.util.Date;

public class Category {
    private String ma;
    private String ten;
    private String moTa;
    private String viTri;


    public Category() {
    }

    public Category(String ma, String ten, String moTa, String viTri) {
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
        this.viTri = viTri;
    }


    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
}
