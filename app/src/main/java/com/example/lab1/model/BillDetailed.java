package com.example.lab1.model;

public class BillDetailed extends Book {
    private int BillDetailId;
    private Bill bill;
    private Book book;
    private int NumberBuy;

    public BillDetailed() {
    }

    public BillDetailed(int billDetailId, Bill bill, Book book, int numberBuy) {
        BillDetailId = billDetailId;
        this.bill = bill;
        this.book = book;
        NumberBuy = numberBuy;
    }

    public int getBillDetailId() {
        return BillDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        BillDetailId = billDetailId;
    }

    public Bill getBill() {
        return this.bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNumberBuy() {//casi nayf laf gif la so luong mua o hoa don chi tiet
        return NumberBuy;
    }

    public void setNumberBuy(int numberBuy) {
        NumberBuy = numberBuy;
    }



//    @NonNull
//    @Override
    public String toString() {
        return "BillDetailed { " +
                "BillDetailId = " + BillDetailId +
                ", bill = " + bill.toString() +
                ", book = " + book.toString()+
                ",NumberBuy = " + NumberBuy +
                '}';

    }



}
