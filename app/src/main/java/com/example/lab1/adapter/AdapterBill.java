package com.example.lab1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab1.R;
import com.example.lab1.db.BillDB;
import com.example.lab1.db.BillDetailedDB;
import com.example.lab1.listview.ListViewBillDetailed;
import com.example.lab1.model.Bill;
import com.example.lab1.model.BillDetailed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterBill extends BaseAdapter {
    private int layout;
    List<Bill> billList;
    List<Bill> list;
    Filter filter;
    BillDB billDB;
    Context context;
    private LayoutInflater layoutInflater;
    BillDetailedDB billDetailedDB;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public AdapterBill(int layout, List<Bill> billList, Context context) {
        this.layout = layout;
        this.billList = billList;
        this.list = billList;
        this.context = context;
        this.billDB = new BillDB(context);
        billDetailedDB = new BillDetailedDB(context);

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return billList.size();
    }

    @Override
    public Object getItem(int position) {
        return billList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        billDB = new BillDB(context);
        convertView = layoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bill, parent, false);
        EditText edBillId = convertView.findViewById(R.id.ed_adapterBillId);
        EditText edBillDate = convertView.findViewById(R.id.ed_adapterBillDate);
        ImageView imgBillDelete = convertView.findViewById(R.id.img_adapterBillDeleteBill);

        imgBillDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!billDetailedDB.checkHoaDon(billList.get(position).getId())){
//                    Toast.makeText(context, "Bạn cần phải xóa hóa đơn chi tiết trc ", Toast.LENGTH_SHORT).show();
//                }else {
                    billDB.deleteBill(billList.get(position).getId());
                    Bill bill = billList.get(position);
                    billList.remove(bill);
                    notifyDataSetChanged();
//                }
//                Toast.makeText(context, "-->" +billList.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListViewBillDetailed.class);
                context.startActivity(intent);
            }
        });

        Bill bill = billList.get(position);
        edBillId.setText(bill.getId());
        edBillDate.setText(sdf.format(bill.getDate()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataSet(List<Bill> arrBill){
            this.billList = arrBill;
            notifyDataSetChanged();
    }

}
