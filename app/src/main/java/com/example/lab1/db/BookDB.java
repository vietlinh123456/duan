package com.example.lab1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1.model.Bill;
import com.example.lab1.model.Book;
import com.example.lab1.mysql.MySQL;

import java.util.ArrayList;
import java.util.List;

public class BookDB {
    private MySQL mySQL;
    private SQLiteDatabase sqLiteDatabase;
    public static final String BOOK_ID = "maSach";
    public static final String BOOK_THELOAI = "theLoaiSach";
    public static final String BOOK_NAME = "tenSach";
    public static final String BOOK_TACGIA = "tacGia";
    public static final String BOOK_NHAXUATBAN = "nhaXuatBan";
    public static final String BOOK_GIA = "gia";
    public static final String BOOK_SOLUONG = "soLuong";
    public static final String TABLE_NAME_BOOK = "sach";
    public static final String BOOL_SQL = "Create table "+TABLE_NAME_BOOK+" ("+
            BOOK_ID+" text primary key ,"+
            BOOK_THELOAI+" text ,"+
            BOOK_NAME+" text ,"+
            BOOK_TACGIA+" text ,"+
            BOOK_NHAXUATBAN+" text ,"+
            BOOK_GIA+" double ,"+
            BOOK_SOLUONG+" integer)";

    public BookDB(Context context){
        mySQL= new MySQL(context);
        sqLiteDatabase = mySQL.getWritableDatabase();
    }
    public long insertBook(Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID,book.getMaSach());
        contentValues.put(BOOK_THELOAI,book.getTheLoaiSach());
        contentValues.put(BOOK_NAME,book.getTenSach());
        contentValues.put(BOOK_TACGIA,book.getTacGia());
        contentValues.put(BOOK_NHAXUATBAN,book.getNhaXuatBan());
        contentValues.put(BOOK_GIA,book.getGia());
        contentValues.put(BOOK_SOLUONG,book.getSoLuong());
        if (sqLiteDatabase.insert(TABLE_NAME_BOOK,null,contentValues)<0){
            return -1;
        }else {
            return 1;
        }
    }

    public List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();
        String query ="Select * from "+TABLE_NAME_BOOK;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Book book = new Book();
            book.setMaSach(cursor.getString(0));
            book.setTheLoaiSach(cursor.getString(1));
            book.setTenSach(cursor.getString(2));
            book.setTacGia(cursor.getString(3));
            book.setNhaXuatBan(cursor.getString(4));
            book.setGia(cursor.getDouble(5));
            book.setSoLuong(cursor.getInt(6));
            bookList.add(book);
            cursor.moveToNext();


        }
        return bookList;
    }
    public long deleteBook(String id){
        if(sqLiteDatabase.delete(TABLE_NAME_BOOK,BOOK_ID+"=?",new String[]{id})<0){
            return -1;
        }else{
            return 1;
        }
    }
    public int updateSach(Book book){
        ContentValues values = new ContentValues();
        values.put(BOOK_ID,book.getMaSach());
        values.put(BOOK_THELOAI,book.getTheLoaiSach());
        values.put(BOOK_NAME,book.getTenSach());
        values.put(BOOK_TACGIA,book.getTacGia());
        values.put(BOOK_NHAXUATBAN,book.getNhaXuatBan());
        values.put(BOOK_GIA,book.getGia());
        values.put(BOOK_SOLUONG,book.getSoLuong());
        int result = sqLiteDatabase.update(TABLE_NAME_BOOK,values,BOOK_ID + " =?", new
                String[]{book.getMaSach()});
        if (result == 0){
            return -1;
        }
        return 1;
    }

  public Book getBookByID(String id){
    Book book = null;
//    String sql="Select "+BOOK_ID+" from "+TABLE_NAME_BOOK;

    Cursor cursor = sqLiteDatabase.query(TABLE_NAME_BOOK,null,BOOK_ID+"=?",new String[]{id},null,null,null);
    Log.d("getBookByID","===>" + cursor.getCount());
    cursor.moveToFirst();
    while (cursor.isAfterLast() == false){
        book = new Book();
        book.setMaSach(cursor.getString(0));
        book.setTheLoaiSach(cursor.getString(1));
        book.setTenSach(cursor.getString(2));
        book.setTacGia(cursor.getString(3));
        book.setNhaXuatBan(cursor.getString(4));
        book.setSoLuong(cursor.getInt(5));
        book.setGia(cursor.getDouble(6));
        break;
    }
    cursor.close();
    return book;
  }
  //GET BOOK TOP 10
  public List<Book> getTop10Book(String month){
      List<Book> dsSach=new ArrayList<>();
      if(Integer.parseInt(month)<10){
          month= "0"+month;
      }
      String SQL=" SELECT maSach, SUM(soLuong) as soluong from hoadonchitiet INNER JOIN hoadon "+
              " ON hoadon.id = hoadonchitiet.id WHERE " +
              " strftime( '%m' ,hoadon.ngayMua)= '"+month+"' "+
              " GROUP BY maSach ORDER BY soluong DESC LIMIT 10 ";
      Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
      cursor.moveToFirst();
      while (cursor.isAfterLast()==false){
          Book sach=new Book();
          sach.setMaSach(cursor.getString(0));
          sach.setSoLuong(cursor.getInt(1));
          sach.setGia((double) 0);
          sach.setTheLoaiSach("");
          sach.setTenSach("");
          sach.setTacGia("");
          sach.setNhaXuatBan("");
          dsSach.add(sach);
          cursor.moveToNext();
      }
      cursor.close();
      return dsSach;
  }

    public double getGiaSach(String maSach){
        String  selections=BOOK_GIA;
        String[] selectionsArg={maSach};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_BOOK,null,selections,selectionsArg,null,null,null);
        cursor.moveToFirst();
        double gia = cursor.getDouble(0);
        cursor.close();
        return gia;
    }
    public int updateBook(Book book, String masach ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_ID,book.getMaSach());
        contentValues.put(BOOK_NAME,book.getTenSach());
        contentValues.put(BOOK_THELOAI,book.getTheLoaiSach());
        contentValues.put(BOOK_TACGIA,book.getTacGia());
        contentValues.put(BOOK_NHAXUATBAN,book.getNhaXuatBan());
        contentValues.put(BOOK_GIA,book.getGia());
        contentValues.put(BOOK_SOLUONG,book.getSoLuong());
        return sqLiteDatabase.update(TABLE_NAME_BOOK,contentValues,BOOK_ID+"=?",new String[]{masach});


    }
}
