package com.example.lab1.model;

import java.util.Date;

public class Bill {
    private String id;
    private Date date;

    public Bill() {
    }

    public Bill(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
