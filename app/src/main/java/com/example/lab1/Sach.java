package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab1.db.BillDB;
import com.example.lab1.db.BookDB;
import com.example.lab1.db.CategoryDB;
import com.example.lab1.listview.ListViewBook;
import com.example.lab1.model.Bill;
import com.example.lab1.model.Book;
import com.example.lab1.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Sach extends AppCompatActivity {
    Button btnLoginSachAdd,btnLoginSachHuy,btnLoginSachShow;
    Spinner spinnerLoginSach;
    String theloaiSach;
    ImageView imgAddTheLoaiSach;
    BookDB bookDB;
    CategoryDB categoryDB;
    EditText edLoginSachId ,edLoginSachTen,edLoginSachNXB,edLoginSachTG,edLoginSachGia,edLoginSachSL;
    List<Category> categoryList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        spinnerLoginSach = findViewById(R.id.sp_loginSach);
        imgAddTheLoaiSach = findViewById(R.id.img_addTheLoaiSach);
        edLoginSachId = findViewById(R.id.ed_loginSachId);
        edLoginSachTen = findViewById(R.id.ed_loginSachTen);
        edLoginSachNXB = findViewById(R.id.ed_loginSachNxb);
        edLoginSachTG = findViewById(R.id.ed_loginSachTacGia);
        edLoginSachGia = findViewById(R.id.ed_loginSachGia);
        edLoginSachSL = findViewById(R.id.ed_loginSachSoLuong);
        btnLoginSachAdd = findViewById(R.id.btn_loginSachAdd);
        btnLoginSachHuy = findViewById(R.id.btn_loginSachCancel);
        btnLoginSachShow = findViewById(R.id.btn_loginSachShow);

        List<String>  stringList = new ArrayList<>();
        CategoryDB categoryDB = new CategoryDB(this);
        stringList = categoryDB.getCategories();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerLoginSach.setAdapter(adapter);

        spinnerLoginSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                theloaiSach = spinnerLoginSach.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgAddTheLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sach.this,TheLoai.class);
                startActivity(intent);
            }
        });

        btnLoginSachAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edLoginSachId.getText().toString();
                String ten = edLoginSachTen.getText().toString();
                String tg = edLoginSachTG.getText().toString();
                String nxb = edLoginSachNXB.getText().toString();
                int sl = Integer.parseInt(edLoginSachSL.getText().toString());
                double gia = Double.parseDouble(edLoginSachGia.getText().toString());
                if(ma.equals("") || ten.equals("") || tg.equals("") || nxb.equals("")
                        || String.valueOf(sl).equals("") || String.valueOf(gia).equals("") ){
                    Toast.makeText(Sach.this, "Không được để trống thông tin, vui lòng điền đầy đủ !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                bookDB = new BookDB(Sach.this);
                Book book = new Book();
                book.setMaSach(edLoginSachId.getText().toString());
                book.setTheLoaiSach(spinnerLoginSach.getSelectedItem().toString());
                book.setTenSach(edLoginSachTen.getText().toString());
                book.setTacGia(edLoginSachTG.getText().toString());
                book.setNhaXuatBan(edLoginSachNXB.getText().toString());
                book.setGia(Double.parseDouble(edLoginSachGia.getText().toString()));
                book.setSoLuong(Integer.parseInt(edLoginSachSL.getText().toString()));


                if(bookDB.insertBook(book) > 0){
                    Toast.makeText(Sach.this, "Thành công !!", Toast.LENGTH_SHORT).show();
                    edLoginSachId.setText("");
                    edLoginSachTen.setText("");
                    edLoginSachTG.setText("");
                    edLoginSachNXB.setText("");
                    edLoginSachGia.setText("");
                    edLoginSachSL.setText("");


                }else{
                    Toast.makeText(Sach.this, "Không thành công !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoginSachShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sach.this, ListViewBook.class);
                startActivity(intent);
            }
        });
    }

}