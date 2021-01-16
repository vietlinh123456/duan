package com.example.lab1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1.model.Category;
import com.example.lab1.model.User;
import com.example.lab1.mysql.MySQL;

import java.util.ArrayList;
import java.util.List;

public class CategoryDB {
    private SQLiteDatabase sqLiteDatabase;
    private MySQL mySQL;
    public static final String CATEGORY_ID = "ma";
    public static final String CATEGORY_NAME ="ten";
    public static final String CATEGORY_MOTA = "moTa";
    public static final String CATEGORY_LOCATION = "viTri";
    public static final String TABLE_NAME_CATEGORY = "theloai";
    public static final String CATEGORY_SQL = "CREATE TABLE "+TABLE_NAME_CATEGORY+"("+
            CATEGORY_ID+" text primary key ,"+
            CATEGORY_NAME+" text,"+
            CATEGORY_MOTA+" text,"+
            CATEGORY_LOCATION+" text)";
    public CategoryDB(Context context){
        mySQL = new MySQL(context);
        sqLiteDatabase = mySQL.getWritableDatabase();
    }
    public long insertCategory(Category category){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_ID,category.getMa());
        contentValues.put(CATEGORY_NAME,category.getTen());
        contentValues.put(CATEGORY_MOTA,category.getMoTa());
        contentValues.put(CATEGORY_LOCATION,category.getViTri());
        if (sqLiteDatabase.insert(TABLE_NAME_CATEGORY,null,contentValues)<0){
            return -1;
        }else{
            return 1;
        }

    }
    public List<Category> getAllCategories(){
        List<Category>  categoryList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_CATEGORY,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Category category = new Category();
            category.setMa(cursor.getString(0));
            category.setTen(cursor.getString(1));
            category.setMoTa(cursor.getString(2));
            category.setViTri(cursor.getString(3));
            categoryList.add(category);
            cursor.moveToNext();
        }
        return categoryList;

    }

    public long deleteCategory(String id){
        if(sqLiteDatabase.delete(TABLE_NAME_CATEGORY,CATEGORY_ID+"=?",new String[]{id})<0){
            return -1;
        }else{
            return 1;
        }
    }
    public List<String> getCategories(){
        List<String> categoryList = new ArrayList<>();
        String query = "SELECT "+CATEGORY_ID+","+CATEGORY_NAME+" from " + TABLE_NAME_CATEGORY;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            String ma = cursor.getString(0);
            String ten = cursor.getString(1);
            String thietLap = ma +" | "+ten;
            categoryList.add(thietLap);
            cursor.moveToNext();
        }
        cursor.close();
        return categoryList;
    }

    public long updateCategory(Category category,String id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_ID,category.getMa());
        contentValues.put(CATEGORY_NAME,category.getTen());
        contentValues.put(CATEGORY_MOTA,category.getMoTa());
        contentValues.put(CATEGORY_LOCATION,category.getViTri());
        int count = sqLiteDatabase.update(TABLE_NAME_CATEGORY,contentValues,CATEGORY_ID+ " =?",new String[]{id});
        if (count ==0){
            return -1;
        }else {
            return 1;
        }

    }
}
