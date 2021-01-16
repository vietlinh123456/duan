package com.example.lab1.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.lab1.db.BillDB;
import com.example.lab1.db.BillDetailedDB;
import com.example.lab1.db.BookDB;
import com.example.lab1.db.CategoryDB;
import com.example.lab1.db.UserDB;
import com.example.lab1.model.BillDetailed;


public class MySQL extends SQLiteOpenHelper {
    public static final String NAME = "duanandroid.db";
    public static final int VERSION = 1;
    public MySQL(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDB.NGUOI_DUNG_SQL);
        db.execSQL(CategoryDB.CATEGORY_SQL);
        db.execSQL(BillDB.Bill_SQL);
        db.execSQL(BookDB.BOOL_SQL);
        db.execSQL(BillDetailedDB.BillDetail_SQL);
        Log.e("SQL",UserDB.NGUOI_DUNG_SQL);
        Log.e("SQL",CategoryDB.CATEGORY_SQL);
        Log.e("SQL",BookDB.BOOL_SQL);
        Log.e("SQL",BillDB.Bill_SQL);
        Log.e("SQL", BillDetailedDB.BillDetail_SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserDB.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CategoryDB.TABLE_NAME_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + BillDB.TABLE_BILL);
        db.execSQL("DROP TABLE IF EXISTS " + BookDB.TABLE_NAME_BOOK);
        db.execSQL("DROP TABLE IF EXISTS "+ BillDetailedDB.TABLE_BILL_DETAILED);
        onCreate(db);
    }
}
