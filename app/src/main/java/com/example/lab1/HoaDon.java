package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab1.adapter.AdapterBill;
import com.example.lab1.db.BillDB;
import com.example.lab1.listview.ListviewBill;
import com.example.lab1.model.Bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HoaDon extends AppCompatActivity {
    ImageView imgDatePicker,imgBillPrev;
    EditText edIdBill,edPickerBill;
    Button btnShow,btnAdd;
    BillDB billDB;
    List<Bill> billList = new ArrayList<>();
    AdapterBill adapterBill = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ADD Hóa đơn");
        setContentView(R.layout.activity_hoa_don);
        imgDatePicker = findViewById(R.id.img_BillDatePicker);
        edIdBill = findViewById(R.id.ed_BillId);
        edPickerBill = findViewById(R.id.ed_BillDate);
        btnShow = findViewById(R.id.btn_BillShow);
        btnAdd = findViewById(R.id.btn_BillAdd);
        imgBillPrev = findViewById(R.id.img_BillPrev);
        edPickerBill.setEnabled(false);
        final Context context = this;
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);

        imgDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HoaDon.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar calendar =new GregorianCalendar(year,month,dayOfMonth);
                                setDate(calendar);
                            }
                        },year,month,day);
                datePickerDialog.show();

        }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edIdBill.getText().toString().equals("") || edPickerBill.getText().toString().equals("")){
                    Toast.makeText(context, "Không được để trống thông tin, vui lòng điền đầy đủ !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {

                    String id = edIdBill.getText().toString();
                    Date date  = sdf.parse(edPickerBill.getText().toString());
                    Bill bill = new Bill(id,date);
                    billDB = new BillDB(HoaDon.this);
                    if(billDB.insertBill(bill) > 0){
                        Toast.makeText(context, "Thành công !!", Toast.LENGTH_SHORT).show();
//                        edIdBill.setText("");
//                        edPickerBill.setText("");
                        Intent intent = new Intent(HoaDon.this,HoaDonChiTiet.class);
                        intent.putExtra("maHd",id);
                        startActivity(intent);

                    }else{
                        Toast.makeText(context, "Không thành công !!", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoaDon.this, ListviewBill.class);
                startActivity(intent);


            }
        });

        imgBillPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoaDon.this,QuanLySach.class);
                startActivity(intent);
            }
        });

    }public void setDate(final Calendar calendar){
        edPickerBill.setText(sdf.format(calendar.getTime()));
    }


//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        billList.clear();
//            try {
//                billList = billDB.getAllBills();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            adapterBill.changeDataSet(billList);
//
//    }
}