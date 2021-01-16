package com.example.lab1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.R;
import com.example.lab1.db.BillDetailedDB;
import com.example.lab1.db.BookDB;
import com.example.lab1.model.BillDetailed;
import com.example.lab1.model.Book;


import java.util.ArrayList;
import java.util.List;

public class AdapterBillDetailed extends BaseAdapter {
    List<BillDetailed> billDetailedList;
    public Activity context;
    public LayoutInflater layoutInflater;
    BillDetailedDB billDetailedDB;

    public AdapterBillDetailed(Activity context,List<BillDetailed> billDetailedList) {
        super();
        this.context = context;
        this.billDetailedList = billDetailedList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        billDetailedDB = new BillDetailedDB(context);
    }

    @Override
    public int getCount() {
        return billDetailedList.size();
    }

    @Override
    public Object getItem(int position) {
        return billDetailedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        TextView txtMasach;
        TextView txtSoluong;
        TextView txtGia;
        TextView txtTien;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView =layoutInflater.inflate(R.layout.adapterbilldetailed,null);
            viewHolder.txtMasach = convertView.findViewById(R.id.tv_adapterBillDetailed_maSach1);
            viewHolder.txtSoluong = convertView.findViewById(R.id.tv_adapterBillDetailed_soLuong1);
            viewHolder.txtGia = convertView.findViewById(R.id.tv_adapterBillDetailed_gia1);
            viewHolder.txtTien = convertView.findViewById(R.id.tv_adapterBillDetailed_tien1);
            viewHolder.imgDelete = convertView.findViewById(R.id.img_adapterBillDetailed_delete1);

            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    billDetailedDB.deleteHoaDonChiTietByID(String.valueOf(billDetailedList.get(position).getBillDetailId()));
                    billDetailedList.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        BillDetailed billDetail = billDetailedList.get(position);
        double gia = billDetail.getBook().getGia();
        double tien = (billDetail.getBook().getGia() ) * (billDetail.getNumberBuy());
        viewHolder.txtMasach.setText("Mã sách: "+billDetail.getBook().getMaSach());
        viewHolder.txtSoluong.setText("Số lượng: "+billDetail.getNumberBuy());
        viewHolder.txtGia.setText("Giá bán: "+ gia + " VNĐ");
        viewHolder.txtTien.setText("Thành tiền: "+ tien + " VNĐ");


        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<BillDetailed> items) {
        this.billDetailedList = items;
        notifyDataSetChanged();
    }
}
