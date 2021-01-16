package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab1.db.CategoryDB;
import com.example.lab1.db.UserDB;
import com.example.lab1.listview.ListViewTheLoai;
import com.example.lab1.model.Category;
import com.example.lab1.model.User;

import java.util.ArrayList;
import java.util.List;

public class TheLoai extends AppCompatActivity {
    Button btnShow,btnHuy,btnThem;
    ImageView imgprevCategory;
    EditText edCategoryID,edCategoryName,edCategoryMota,edCategoryLocation;
    CategoryDB categoryDB;
    List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

        btnShow = findViewById(R.id.btn_danhSachCategory);
        imgprevCategory = findViewById(R.id.img_prevCaregory);
        btnHuy = findViewById(R.id.btn_huyCategory);
        btnThem = findViewById(R.id.btn_themCategory);
        edCategoryID = findViewById(R.id.ed_maTheLoai);
        edCategoryName = findViewById(R.id.ed_tenTheLoai);
        edCategoryMota = findViewById(R.id.ed_moTaTheLoai);
        edCategoryLocation = findViewById(R.id.ed_viTriTheLoai);

        categoryList=new ArrayList<>();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maTl = edCategoryID.getText().toString();
                String tenTl = edCategoryName.getText().toString();
                String moTaTl = edCategoryMota.getText().toString();
                String viTriTl = edCategoryLocation.getText().toString();
                if(maTl.equals("")||tenTl.equals("")||moTaTl.equals("")||viTriTl.equals("")){
                    Toast.makeText(TheLoai.this, "Vui lòng điền đầy đủ thông tin !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                categoryDB = new CategoryDB(TheLoai.this);
                Category category = new Category();
                category.setMa(edCategoryID.getText().toString());
                category.setTen(edCategoryName.getText().toString());
                category.setMoTa(edCategoryMota.getText().toString());
                category.setViTri(edCategoryLocation.getText().toString());
                categoryList.add(category);
//                check();
                if(categoryDB.insertCategory(category)<0){
                    Toast.makeText(TheLoai.this, "Insert Không thành công !!", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(TheLoai.this, " Insert Thành công !!", Toast.LENGTH_SHORT).show();
                    edCategoryID.setText("");
                    edCategoryName.setText("");
                    edCategoryMota.setText("");
                    edCategoryLocation.setText("");
                }

            }
        });




        imgprevCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheLoai.this,QuanLySach.class);
                startActivity(intent);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheLoai.this,ListViewTheLoai.class);
                startActivity(intent);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//    public  void check(){
//
//    }
}