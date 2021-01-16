package com.example.lab1.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1.R;
import com.example.lab1.Sach;
import com.example.lab1.adapter.AdapterBill;
import com.example.lab1.adapter.AdapterBook;
import com.example.lab1.db.BillDB;
import com.example.lab1.db.BookDB;
import com.example.lab1.model.Bill;
import com.example.lab1.model.Book;

import java.util.List;

public class ListViewBook extends AppCompatActivity {
    ImageView imgListviewAddBook, imgLisviewPrevBook;
    ListView lvShowBook;
    EditText edSearch;
    BookDB bookDB;
    private List<Book>  bookList;
    AdapterBook adapterBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_book);
        edSearch = findViewById(R.id.ed_searchIdBook);
        imgListviewAddBook = findViewById(R.id.img_listviewAddSach);
        imgLisviewPrevBook = findViewById(R.id.img_listviewPrevBook);
        lvShowBook = findViewById(R.id.lv_showBook);
        Toast.makeText(this, "Please !! Click on Item make update !!", Toast.LENGTH_SHORT).show();

        bookDB = new BookDB(ListViewBook.this);
        bookList = bookDB.getAllBooks();
        adapterBook = new AdapterBook(bookList,this);
        lvShowBook.setAdapter(adapterBook);

        imgLisviewPrevBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgListviewAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListViewBook.this, Sach.class);
                startActivity(intent);
            }
        });
        lvShowBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bill bill= (Bill) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListViewBook.this,ListViewBillDetailed.class);
                Bundle bundle = new Bundle();
                bundle.putString("MAHOADON",bill.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        lvShowBook.setTextFilterEnabled(true);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.print("Text [" + s +"] - Start [" + start + "] - Before [" + after + "] - Count [" + count + "]");
                if (count < after){
                    adapterBook.resetData();
                }
                adapterBook.getFilter().filter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}