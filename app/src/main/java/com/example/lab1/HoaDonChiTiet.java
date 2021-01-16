package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.adapter.AdapterBillDetailed;
import com.example.lab1.db.BillDB;
import com.example.lab1.db.BillDetailedDB;
import com.example.lab1.db.BookDB;
import com.example.lab1.model.Bill;
import com.example.lab1.model.BillDetailed;
import com.example.lab1.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTiet extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhToan;
    Button btnThanhToan, btnThem;
    BillDetailedDB billDetailedDB;
    BookDB bookDB;
    public List<BillDetailed> billDetailedList = new ArrayList<>();
    public List<String> billList = new ArrayList<>();
    public  List<Book> bookList = new ArrayList<>();
    ListView lv;
    AdapterBillDetailed adapterBillDetailed;
    double thanhtien = 0;
    BillDB billDB;
    String maHD,maSach,soLuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        edMaSach = findViewById(R.id.ed_BillDeatailed_maSach);
        edMaHoaDon = findViewById(R.id.ed_BillDetailed_maHoaDon);
        edSoLuong = findViewById(R.id.ed_BillDetailed_soLuong);
        tvThanhToan = findViewById(R.id.tv_billdetauled_thanhToan);
        btnThem = findViewById(R.id.btn_BillDetailed_Them);
        btnThanhToan = findViewById(R.id.btn_BillDetailed_ThanhToan);
        lv = findViewById(R.id.lv_billDetailed);


        billDetailedDB = new BillDetailedDB(this);
        adapterBillDetailed =new AdapterBillDetailed(this,billDetailedList);
        lv.setAdapter(adapterBillDetailed);
//
//        Intent in = getIntent();
//        Bundle bundle =in.getExtras();
//        if (bundle != null){
//            edMaHoaDon.setText(bundle.getString("MAHOADON"));
//        }

    //DB
        billDetailedDB = new BillDetailedDB(this);
        bookDB = new BookDB(this);
        billDB = new BillDB(this);
        //lấy mã qua Intent
        Intent  intent = getIntent();
        edMaHoaDon.setText(intent.getStringExtra("maHd"));
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (validation() <0){
                        Toast.makeText(HoaDonChiTiet.this, "Vui lòng nhập đầy đủ thông tin !!", Toast.LENGTH_SHORT).show();
                    }else {
                        Book book = bookDB.getBookByID(edMaSach.getText().toString());
                        if (book != null){
                            int pos  = checkMaSach(billDetailedList,edMaSach.getText().toString());
                            Bill bill  = new Bill(edMaHoaDon.getText().toString(), new Date());
                            BillDetailed billDetailed = new BillDetailed(1,bill,book,Integer.parseInt(edSoLuong.getText().toString()));
                            if (pos >= 0){
                                int soLuong  = billDetailedList.get(pos).getNumberBuy();
                                billDetailed.setNumberBuy( soLuong + Integer.parseInt(edSoLuong.getText().toString()));
                                billDetailedList.set(pos,billDetailed);
                            }else {
                                billDetailedList.add(billDetailed);
                                Toast.makeText(HoaDonChiTiet.this, "Thành công !!", Toast.LENGTH_SHORT).show();
//                                edSoLuong.setText("");
//                                edMaHoaDon.setText("");
//                                edMaSach.setText("");
                            }
                            adapterBillDetailed.changeDataset(billDetailedList);
                        }else {
                            Toast.makeText(HoaDonChiTiet.this, "Mã sách không tồn tại vui lòng nhập chính xác !@!@", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Log.e("Error " , e.toString());
                }
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billDetailedDB = new BillDetailedDB(HoaDonChiTiet.this);
                thanhtien =0;
                try {
                    for (BillDetailed hd : billDetailedList){
                        billDetailedDB.inserHoaDonChiTiet(hd);
                        thanhtien = thanhtien + (hd.getNumberBuy() * hd.getBook().getGia() *1000 );
                    }
                    tvThanhToan.setText("Tổng tiền : " + thanhtien + " VND");
                }catch (Exception e){
                    Log.e("Error" , e.toString());
                }
            }
        });
    }
    //check validate
    public int validation(){
        if (edMaHoaDon.getText().toString().isEmpty() || edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty()){
            return -1;
        }else {
            return 1;
        }
    }
    //check ma sách
    public int checkMaSach(List<BillDetailed> list ,String  maSach){
        int pos =-1;
        for (int  i =0; i<list.size();i++){
            BillDetailed hd = list.get(i);
            if (hd.getBook().getMaSach().equalsIgnoreCase(maSach)){
                pos =i;
                break;
            }
        }
        return pos;
    }
































//    public void getDuLieu(){
//         maHD = edMaHoaDon.getText().toString();
//         maSach = edMaSach.getText().toString();
//         soLuong = edSoLuong.getText().toString();
//        billList = billDB.getIDBill();
//        bookList = bookDB.getAllBooks();
//    }
//    public void doDuLieu(){
////        billDetailedList = billDetailedDB.getALlBillDetaileds();
////        adapterBillDetailed = new AdapterBillDetailed(HoaDonChiTiet.this,billDetailedList);
////        lv.setAdapter(adapterBillDetailed);
//    }
//    public void validate(){
//        getDuLieu();
//        for(BillDetailed hd : billDetailedList){
//            if (hd.equals(maHD)){
//
//            }
//        }
//    }
}