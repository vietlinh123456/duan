package com.example.lab1.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lab1.R;
import com.example.lab1.TheLoai;
import com.example.lab1.adapter.AdapterCategory;
import com.example.lab1.db.CategoryDB;
import com.example.lab1.model.Category;

import java.util.ArrayList;
import java.util.List;

public class ListViewTheLoai extends AppCompatActivity {
    ListView lvCategory;
    ImageView imgPrevCategory,imgAddCategory,imgLogOutCategory;
    CategoryDB categoryDB;
    public  static List<Category> categoryList;
    AdapterCategory adapterCategory =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_the_loai);
        lvCategory =(ListView) findViewById(R.id.lv_category);
        imgAddCategory = findViewById(R.id.img_addCategory);
        imgLogOutCategory = findViewById(R.id.img_logOutCategory);
        imgPrevCategory = findViewById(R.id.img_prevListViewCategory);

        categoryDB = new CategoryDB(ListViewTheLoai.this);
        categoryList = categoryDB.getAllCategories();
        adapterCategory = new AdapterCategory(getApplicationContext(),categoryList,R.layout.adaptercategory);
        adapterCategory.notifyDataSetChanged();
        lvCategory.setAdapter(adapterCategory);


        imgPrevCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ListViewTheLoai.this, TheLoai.class);
                startActivity(intent2);
            }
        });

        imgAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewTheLoai.this, TheLoai.class);
                startActivity(intent);
            }
        });

        imgLogOutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}