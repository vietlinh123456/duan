package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lab1.db.BillDetailedDB;

public class ThongKe extends AppCompatActivity {
    TextView tvNgay,tvThang,tvNam;
    BillDetailedDB billDetailedDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tvNgay = findViewById(R.id.tv_doanhthuNgay);
        tvThang = findViewById(R.id.tv_doanhthuThang);
        tvNam = findViewById(R.id.tv_doanhthuNam);
        billDetailedDB = new BillDetailedDB(ThongKe.this);
        tvNgay.setText("Hôm nay : "+billDetailedDB.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này : "+billDetailedDB.getDoanhThuTheoThang());
        tvNam.setText("Năm này : "+ billDetailedDB.getDoanhThuTheoNam());
    }
}