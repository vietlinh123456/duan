package com.example.lab1.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.lab1.NguoiDung;
import com.example.lab1.QuanLySach;
import com.example.lab1.R;

import com.example.lab1.adapter.AdapterUser;
import com.example.lab1.db.UserDB;
import com.example.lab1.model.User;
import com.example.lab1.mysql.MySQL;

import java.util.List;

public class ListViewNguoiDung extends AppCompatActivity {
    ImageView imgPrevListViewUser,imgAddUser,imgLogOutUser;
    ListView lvUser;
    UserDB userDB;
    public static List<User> userList;
    MySQL mySQL;
    AdapterUser adapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_nguoi_dung);
        lvUser =findViewById(R.id.lv_nguoiDung);
        imgPrevListViewUser = findViewById(R.id.img_prevListViewUser);
        imgLogOutUser = findViewById(R.id.img_logOutUser);
        imgAddUser = findViewById(R.id.img_addUser);

        userDB= new UserDB(ListViewNguoiDung.this);
        userList =userDB.getAllUsers();
        adapterUser = new AdapterUser(this,userList,R.layout.adapter_user);
        lvUser.setAdapter(adapterUser);






        imgAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewNguoiDung.this,NguoiDung.class);
                startActivity(intent);
            }
        });

        imgLogOutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        imgPrevListViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewNguoiDung.this, NguoiDung.class);
                startActivity(intent);
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_user,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.add :
//                Intent intent = new Intent(ListViewNguoiDung.this,NguoiDung.class);
//                startActivity(intent);
//                return true;
//            case R.id.changePass:
//                Intent intent2 = new Intent(ListViewNguoiDung.this, ChangePassUser.class);
//                startActivity(intent2);
//                return true;
//            case R.id.logOut:
//                finish();
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}