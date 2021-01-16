package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1.adapter.AdapterBook;
import com.example.lab1.db.BookDB;
import com.example.lab1.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BanChay extends AppCompatActivity {
    public static List<Book> bookList = new ArrayList<>();
    ListView listViewbook;
    AdapterBook adapterBook = null;
    BookDB bookDB;
    EditText edThang;
    Button btnTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_chay);
        setTitle("TOP 10 SÁCH BÁN CHẠY");
        listViewbook = findViewById(R.id.lv_top10Book);
        edThang = findViewById(R.id.ed_thang);
        btnTim = findViewById(R.id.btn_top10Sach);



        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(edThang.getText().toString()) >= 13 || Integer.parseInt(edThang.getText().toString()) < 0){
                    Toast.makeText(BanChay.this, "Vui lòng nhập đúng tháng ", Toast.LENGTH_SHORT).show();

                }else {
                    bookDB = new BookDB(BanChay.this);
                    bookList = bookDB.getTop10Book(edThang.getText().toString());
                    adapterBook = new AdapterBook(bookList,BanChay.this);
                    listViewbook.setAdapter(adapterBook);
//                    bookDB = new BookDB(BanChay.this);
//                    bookList  = bookDB.getAllBooks();
//                    adapterBook = new AdapterBook(bookList,BanChay.this);
//                    listViewbook.setAdapter(adapterBook);
                }
            }
        });
    }
}