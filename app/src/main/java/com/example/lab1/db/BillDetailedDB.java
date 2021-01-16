package com.example.lab1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1.model.Bill;
import com.example.lab1.model.BillDetailed;
import com.example.lab1.model.Book;
import com.example.lab1.mysql.MySQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDetailedDB {
    private SQLiteDatabase sqLiteDatabase;
    private MySQL mySQL;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String COLUMN_BILLDETAIL_ID = "maHoaDonChiTiet";
    public static final String COLUMN_BILLDETAIL_BOOK = "maSach";
    public static final String COLUMN_BILLDETAIL_BILL = "id";
    public static final String COLUMN_BILLDETAIL_NUMBER = "soLuong";
    public static final String TABLE_BILL_DETAILED = "hoadonchitiet";
    public static final String TAG = "hoadonchitiet";
    public static final String BillDetail_SQL = "create table "+
            TABLE_BILL_DETAILED +" ("+
            COLUMN_BILLDETAIL_ID +" integer primary key autoincrement, "+
            COLUMN_BILLDETAIL_BOOK +" text not null, " +
            COLUMN_BILLDETAIL_BILL + " text not null, " +
            COLUMN_BILLDETAIL_NUMBER + " integer );";

    public BillDetailedDB(Context context) {
        mySQL = new MySQL(context);
        sqLiteDatabase = mySQL.getWritableDatabase();
    }

    public int inserHoaDonChiTiet(BillDetailed billDetailed){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BILLDETAIL_BOOK,billDetailed.getBook().getMaSach());
        values.put(COLUMN_BILLDETAIL_BILL,billDetailed.getBill().getId());
        values.put(COLUMN_BILLDETAIL_NUMBER,billDetailed.getNumberBuy());

            if(sqLiteDatabase.insert(TABLE_BILL_DETAILED,null,values) <0){
                return -1;
            }else{

        return 1;
            }
    }
    //get all hóa đơn chi tiết
    public List<BillDetailed> getAllDetaileds() throws ParseException {
        List<BillDetailed> billDetailedList = new ArrayList<>();
        String sql ="Select "+COLUMN_BILLDETAIL_ID+" , hoadon.id, hoadon.ngayMua, " +
                "sach.maSach, sach.theLoaiSach, sach.tenSach, sach.tacGia, sach.nhaXuatBan, sach.gia, sach.soLuong, hoadonchitiet.soLuong " +
                "from hoadonchitiet inner join hoadon on hoadonchitiet.id = hoadon.id " +
                "inner join sach on sach.maSach = hoadonchitiet.maSach";
        Cursor cursor =sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        try {


            while (cursor.isAfterLast() == false) {
                BillDetailed billDetailed = new BillDetailed();
                billDetailed.setBillDetailId(cursor.getInt(0));
                billDetailed.setBill(new Bill(cursor.getString(1), sdf.parse(cursor.getString(2))));
                billDetailed.setBook(new Book(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getDouble(8), cursor.getInt(9)));
                billDetailed.setNumberBuy(cursor.getInt(10));
                billDetailedList.add(billDetailed);
                Log.e("//=== ", billDetailed.toString());
                cursor.moveToNext();
            }
            cursor.close();
        }catch (Exception e){
            Log.d(TAG,e.toString());
        }
        return billDetailedList;
    }
    //get all hoa don chi tiet by id
    public List<BillDetailed> getBillDetailByID(String mahd) throws ParseException {
        List<BillDetailed> billDetailedList = new ArrayList<>();
        String sql ="Select "+COLUMN_BILLDETAIL_ID+" , hoadon.id, hoadon.date " +
                " sach.maSach, sach.theLoaiSach, sach.tenSach, sach.tacGia, sach.nhaXuatBan, sach.gia, sach.soLuong, hoadonchitiet.soLuong " +
                " from hoadonchitiet inner join hoadon on hoadonchitiet.id = hoadon.id" +
                " inner join sach.maSach = hoadonchitiet.maSach " +
                " where hoadonchitiet.id = '"+ mahd +"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast() == false) {
                BillDetailed billDetailed = new BillDetailed();
                billDetailed.setBillDetailId(cursor.getInt(0));
                billDetailed.setBill(new Bill(cursor.getString(1), sdf.parse(cursor.getString(2))));
                billDetailed.setBook(new Book(cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getDouble(8), cursor.getInt(9)));
                billDetailed.setNumberBuy(cursor.getInt(10));
                billDetailedList.add(billDetailed);
                cursor.moveToNext();
            }
            cursor.close();
        }catch (Exception e){
            Log.d(TAG,e.toString());
        }
        return billDetailedList;
    }

    //xoa hoa don chi tiet
    public long deleteHoaDonChiTietByID(String mahdct){
        int result = sqLiteDatabase.delete(TABLE_BILL_DETAILED,COLUMN_BILLDETAIL_ID+"=?",new String[]{mahdct});
        if (result < 0){
            return -1;
        }else {
            return 1;
        }
    }

    //check hóa đơn
    public boolean checkHoaDon(String maHoaDon){
        // select
        String[] columns = {"id"};
        //where clause
        String selection = "id=?";
        //where clause argments
        String[] selectionArgs ={maHoaDon};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(TABLE_BILL_DETAILED,columns,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();
            int i =cursor.getCount();
            cursor.close();
            if (i <=0){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            Log.e("Error",e.toString());
            return false;
        }
    }
    //get doanh thu theo ngày
    public double getDoanhThuTheoNgay(){
        double doanhThu =0;
        String sql = "Select sum(tongtien) from (select sum(sach.gia * hoadonchitiet.soLuong ) as 'tongtien'" +
                " from hoadon inner join hoadonchitiet on hoadon.id = hoadonchitiet.id" +
                " inner join sach on hoadonchitiet.maSach = sach.maSach where hoadon.ngayMua = date('now')" +
                " group by hoadonchitiet.maSach )tmp";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(){
        double doanhThu =0;
        String sql = "Select sum(tongtien) " +
                " from (select sum(sach.gia * hoadonchitiet.soLuong ) as 'tongtien' " +
                " from hoadon " +
                " inner join hoadonchitiet on hoadon.id = hoadonchitiet.id" +
                " inner join sach on hoadonchitiet.maSach = sach.maSach " +
                " where strftime('%m', hoadon.ngayMua) = strftime('%m','now')" +
                " group by hoadonchitiet.maSach)tmp ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu =0;
        String sql = "Select sum(tongtien) from (select sum(sach.gia * hoadonchitiet.soLuong ) as 'tongtien'" +
                " from hoadon inner join hoadonchitiet on hoadon.id = hoadonchitiet.id" +
                " inner join sach on hoadonchitiet.maSach = sach.maSach " +
                " where strftime('%Y', hoadon.ngayMua) = strftime('%Y','now')" +
                " group by hoadonchitiet.maSach)tmp ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            doanhThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }

    }


