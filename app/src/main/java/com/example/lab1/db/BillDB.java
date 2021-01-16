package com.example.lab1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1.model.Bill;
import com.example.lab1.model.User;
import com.example.lab1.mysql.MySQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDB {
    private SQLiteDatabase sqLiteDatabase;
    private MySQL mySQL;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String COLUMN_BILL_ID = "id";
    public static final String COLUMN_BILL_DATE = "ngayMua";
    public static final String TABLE_BILL = "hoadon";
    public static final String Bill_SQL = "create table " +
             TABLE_BILL +" ( "+
            COLUMN_BILL_ID +" text primary key ,"+
            COLUMN_BILL_DATE +" date);";

    public BillDB(Context context){
        mySQL = new MySQL(context);
        sqLiteDatabase = mySQL.getWritableDatabase();
    }
    public int insertBill(Bill bill){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BILL_ID,bill.getId());
        contentValues.put(COLUMN_BILL_DATE,sdf.format(bill.getDate()));
        if(sqLiteDatabase.insert(TABLE_BILL,null,contentValues)<0){
            return -1;
        }else{
            return 1;
        }
    }

    public List<Bill> getAllBills() throws ParseException {
        List<Bill> billList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_BILL, null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Bill bill = new Bill();
            bill.setId(cursor.getString(0));
            bill.setDate(sdf.parse(cursor.getString(1)));
            billList.add(bill);
            cursor.moveToNext();

        }
        return billList;


    }
    public List<String> getIDBill(){
        List<String> billList = new ArrayList<>();
        String sql = "Select id from hoadon";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
           String ma =cursor.getString(0);
            billList.add(ma);
            cursor.moveToNext();
        }cursor.close();
        return billList;
    }

    public long deleteBill(String id){
        if(sqLiteDatabase.delete(TABLE_BILL,COLUMN_BILL_ID+"=?",new String[]{id})<0){
            return -1;
        }else{
            return 1;
        }

    }
    //update
    public int updateHoaDon(Bill bill){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BILL_ID,bill.getId());
        values.put(COLUMN_BILL_DATE,bill.getDate().toString());
        int result = sqLiteDatabase.update(TABLE_BILL,values,COLUMN_BILL_ID+"=?", new
                String[]{bill.getId()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
}
