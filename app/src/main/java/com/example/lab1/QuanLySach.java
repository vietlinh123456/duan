package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lab1.listview.ListViewNguoiDung;
import com.example.lab1.listview.ListViewTheLoai;

public class QuanLySach extends AppCompatActivity {
    ImageView imgNguoiDung,imgTheLoai,imgSach,imgHoaDon,imgBanChay,imgThongKe,imgPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);
        imgNguoiDung = findViewById(R.id.img_nguoiDung);
        imgSach = findViewById(R.id.img_sach);
        imgTheLoai = findViewById(R.id.img_theLoai);
        imgHoaDon = findViewById(R.id.img_hoaDon);
        imgBanChay = findViewById(R.id.img_banChay);
        imgThongKe = findViewById(R.id.img_thongKe);
        imgPrev = findViewById(R.id.img_prev);

        imgNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this, NguoiDung.class);
                startActivity(intent);
            }
        });

        imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this,Sach.class);
                startActivity(intent);
            }
        });

        imgTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this, TheLoai.class);
                startActivity(intent);
            }
        });

        imgHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this,HoaDon.class);
                startActivity(intent);
            }
        });

        imgBanChay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this,BanChay.class);
                startActivity(intent);
            }
        });

        imgThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this,ThongKe.class);
                startActivity(intent);
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLySach.this,LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}