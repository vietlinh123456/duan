package com.example.lab1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1.model.User;
import com.example.lab1.mysql.MySQL;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private SQLiteDatabase sqLiteDatabase;
    private MySQL mySQL;
    public static final String COLUMN_ID = "ten";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_NAME = "name";
    public static final String TAG = "NguoiDungDao"; // check lỗi
    public static final String TABLE_NAME = "nguoidung";
    public static final String NGUOI_DUNG_SQL = "Create table nguoidung(" +
            COLUMN_ID + " text primary key ," +
            COLUMN_PASS + " text," +
            COLUMN_PHONE + " text," +
            COLUMN_NAME + " text)";

    public UserDB(Context context) {
        mySQL = new MySQL(context);
        sqLiteDatabase = mySQL.getWritableDatabase();
    }


    public int insertUsers(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getTen());
        contentValues.put(COLUMN_PASS, user.getPass());
        contentValues.put(COLUMN_PHONE, user.getPhone());
        contentValues.put(COLUMN_NAME, user.getHoten());

        if (sqLiteDatabase.insert(TABLE_NAME, null, contentValues) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            User user = new User();
            user.setTen(cursor.getString(0));
            user.setPass(cursor.getString(1));
            user.setPhone(cursor.getString(2));
            user.setHoten(cursor.getString(3));
            userList.add(user);
            cursor.moveToNext();
        }
        return userList;
    }

    public long deleteUser(String id) {

        if (sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{id}) < 0) {
            return -1;
        } else {
            return 1;
        }
//        String selection = COLUMN_ID + "=?";
//        String[] selectionArgs = {id};
//        int deleteRowUser = sqLiteDatabase.delete(COLUMN_ID,selection,selectionArgs);
//
//        return deleteRowUser;
    }

    public long updateUser(User user, String id) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getTen());
        contentValues.put(COLUMN_PHONE, user.getPhone());
        contentValues.put(COLUMN_NAME, user.getHoten());

        String selection = COLUMN_ID + "=?";

        String[] selectionArgs = {user.getTen()};
        return sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{id});

//        if(count < 0){
//            return -1;
//        }else{
//            return 1;
//        }

    }

    public long updatePasswordUser(User user) {
        sqLiteDatabase = mySQL.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PASS, user.getPass());

        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {user.getPass()};

        int count = sqLiteDatabase.update(TABLE_NAME, contentValues, selection, selectionArgs);

        return count;

    }


}
