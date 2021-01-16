package com.example.lab1.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lab1.R;
import com.example.lab1.adapter.AdapterBillDetailed;
import com.example.lab1.db.BillDetailedDB;
import com.example.lab1.model.BillDetailed;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListViewBillDetailed extends AppCompatActivity {
    public List<BillDetailed> billDetailedList = new ArrayList<>();
    ListView lv;
    AdapterBillDetailed adapterBillDetailed = null;
    BillDetailedDB billDetailedDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_bill_detailed);
        setTitle("List Hóa đơn chi tiết");
        lv = findViewById(R.id.lv_billDetailed);

        try {
            billDetailedDB = new BillDetailedDB(ListViewBillDetailed.this);
            billDetailedList = billDetailedDB.getAllDetaileds();
            adapterBillDetailed = new AdapterBillDetailed(ListViewBillDetailed.this,billDetailedList);
            lv.setAdapter(adapterBillDetailed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Intent intent = getIntent();
//        Bundle bundle =intent.getExtras();
//        if(bundle != null){
//            try {
//                billDetailedList =  billDetailedDB.getBillDetailByID(bundle.getString("maHd"));
//                adapterBillDetailed = new AdapterBillDetailed(ListViewBillDetailed.this,billDetailedList);
//                lv.setAdapter(adapterBillDetailed);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
}
}