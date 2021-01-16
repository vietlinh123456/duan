package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab1.db.UserDB;
import com.example.lab1.listview.ListViewNguoiDung;
import com.example.lab1.model.User;

public class NguoiDung extends AppCompatActivity {

    Button btnHuy,btnThem,btnListUser;
    ImageView imgPrevUser;
    EditText edTen,edPass,edPassAgain,edPhone,edHoTen;
    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        btnThem = findViewById(R.id.btn_themUser);
        btnHuy = findViewById(R.id.btn_huyUser);
        btnListUser = findViewById(R.id.btn_listUser);
        imgPrevUser = findViewById(R.id.img_prevUser);
        edTen = findViewById(R.id.ed_idUser);
        edPass = findViewById(R.id.ed_passUser);
        edPassAgain = findViewById(R.id.ed_passAgain);
        edPhone = findViewById(R.id.ed_phoneUser);
        edHoTen = findViewById(R.id.ed_nameUser);



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht = edHoTen.getText().toString();
                String ten = edTen.getText().toString();
                String pas = edPass.getText().toString();
                String Password = edPassAgain.getText().toString();
                String phon = edPhone.getText().toString();
                String regex = "^\\w{1,}@\\w{1,}\\.\\w{1,}$";
//                if (!edEmail.getText().toString().matches(regex)) {
//                    Toast.makeText(MainActivity.this, "Email không đúng định dạng", Toast.LENGTH_LONG).show();
//                    return;
//                }
                if(ten.equals("")||pas.equals("")||Password.equals("")||phon.equals("")||ht.equals("")){
                    Toast.makeText(NguoiDung.this, "Vui lòng điền đầy đủ thông tin !!", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!pas.equalsIgnoreCase(Password)){
                    Toast.makeText(NguoiDung.this, "Mật khẩu phải trùng khớp vs nhau !!", Toast.LENGTH_SHORT).show();
                    return ;
                }

                userDB = new UserDB(NguoiDung.this);
                User user = new User();
                user.setTen(edTen.getText().toString());
                user.setHoten(edHoTen.getText().toString());
                user.setPass(edPass.getText().toString());
                user.setPhone(edPhone.getText().toString());


                if(userDB.insertUsers(user)>0){
                    Toast.makeText(NguoiDung.this, " Insert Thành công !!", Toast.LENGTH_SHORT).show();
                    edTen.setText("");
                    edHoTen.setText("");
                    edPass.setText("");
                    edPassAgain.setText("");
                    edPhone.setText("");
                }else {
                    Toast.makeText(NguoiDung.this, "Insert Không thành công !!", Toast.LENGTH_SHORT).show();
                }


            }
        });




        imgPrevUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NguoiDung.this,QuanLySach.class);
                startActivity(intent);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NguoiDung.this,ListViewNguoiDung.class);
                startActivity(intent);
            }
        });




    }




}