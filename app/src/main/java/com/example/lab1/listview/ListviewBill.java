package com.example.lab1.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lab1.HoaDon;
import com.example.lab1.R;
import com.example.lab1.adapter.AdapterBill;
import com.example.lab1.db.BillDB;
import com.example.lab1.model.Bill;

import java.text.ParseException;
import java.util.List;

public class ListviewBill extends AppCompatActivity {
    ImageView img_ListviewBillPrev,img_ListviewBillLogOut,img_ListviewBillAdd;
    ListView listViewBill;
    BillDB billDB;
    private List<Bill> billList;
    AdapterBill adapterBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_bill);
        img_ListviewBillAdd = findViewById(R.id.img_listviewBillAdd);
        img_ListviewBillLogOut = findViewById(R.id.img_listviewBillLogOut);
        img_ListviewBillPrev = findViewById(R.id.img_listviewBillPrev);
        listViewBill = findViewById(R.id.lv_listviewBillDanhSach);

        billDB = new BillDB(ListviewBill.this);
        try {
            billList = billDB.getAllBills();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterBill = new AdapterBill(R.layout.adapter_bill,billList,this);
        listViewBill.setAdapter(adapterBill);

        img_ListviewBillPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListviewBill.this, HoaDon.class);
                startActivity(intent);
            }
        });
        img_ListviewBillLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_ListviewBillAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListviewBill.this, HoaDon.class);
                startActivity(intent);
            }
        });
//        listViewBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Bill bill = (Bill) parent.getItemAtPosition(position);
//                Intent intent = new Intent(ListviewBill.this,ListViewBillDetailed.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("MAHOADONCHITIET",bill.getId());
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//        listViewBill.setTextFilterEnabled(true);
}
}