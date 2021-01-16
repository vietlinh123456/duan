package com.example.lab1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab1.R;
import com.example.lab1.db.BookDB;
import com.example.lab1.model.Book;

import java.util.ArrayList;
import java.util.List;

public class AdapterBook extends BaseAdapter implements Filterable {
    private int layout;
    List<Book> bookList;
    List<Book> list;
    Filter filter;
    BookDB bookDB;
    Context context;
    Book book;

    private LayoutInflater layoutInflater;

    public AdapterBook( List<Book> bookList, Context context) {

        this.bookList = bookList;
        this.list = bookList;
        this.context = context;

        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bookDB = new BookDB(context);
        convertView = layoutInflater.from(parent.getContext()).inflate(R.layout.adapter_book, parent, false);
        final EditText edMaAdapter = convertView.findViewById(R.id.ed_adapterMaSach);
        ImageView imgBoolDelete = convertView.findViewById(R.id.img_adapterDeleteSach);
        book = bookList.get(position);
        imgBoolDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDB.deleteBook(bookList.get(position).getMaSach());
                Book book = bookList.get(position);
                bookList.remove(book);
                notifyDataSetChanged();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "update ở đây !!", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.updatebook);
                final EditText edMa = dialog.findViewById(R.id.ed_updateboookMaSach);
                final  EditText edTen = dialog.findViewById(R.id.ed_updatebookTenSach);
                final  EditText edTl = dialog.findViewById(R.id.ed_updatebookTheLoai);
                final  EditText edTacGia = dialog.findViewById(R.id.ed_updatebookTacGia);
                final  EditText edNXB = dialog.findViewById(R.id.ed_updatebookNXB);
                final  EditText edGia = dialog.findViewById(R.id.ed_updatebookGia);
                final  EditText edSl = dialog.findViewById(R.id.ed_updatebookSoLuong);
                final ImageView imgUpdateBook = dialog.findViewById(R.id.img_updatebookUpdate);
                edMa.setEnabled(false);
                book = bookList.get(position);
                edMa.setText(book.getMaSach());
                edTen.setText(book.getTenSach());
                edTl.setText(book.getTheLoaiSach());
                edTacGia.setText(book.getTacGia());
                edNXB.setText(book.getNhaXuatBan());
                edGia.setText(String.valueOf(book.getGia()) );
                edSl.setText(String.valueOf(book.getSoLuong()));
                imgUpdateBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    book = bookList.get(position);
                    book.setMaSach(edMa.getText().toString());
                    book.setTenSach(edTen.getText().toString());
                    book.setTheLoaiSach(edTl.getText().toString());
                    book.setTacGia(edTacGia.getText().toString());
                    book.setNhaXuatBan(edNXB.getText().toString());
                    book.setGia(Double.parseDouble(edGia.getText().toString()));
                    book.setSoLuong(Integer.parseInt(edSl.getText().toString()));
                    bookDB = new BookDB(context);
                    long update = bookDB.updateBook(book,bookList.get(position).getMaSach());
                    if (update > 0){
                        Toast.makeText(context, "Update Thành công !!", Toast.LENGTH_SHORT).show();
                        bookList.set(position,book);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Update không thành công !!", Toast.LENGTH_SHORT).show();
                    }


                    }
                });
                dialog.show();

            }
        });
        book = bookList.get(position);
        edMaAdapter.setText(book.getMaSach());


        return convertView;
    }

    //search Book
    @Override
    public Filter getFilter() {
        if (filter ==null){
            filter  = new CustomFilter();
        }
        return filter;
    }
    public void resetData(){
        bookList = list;
    }
    public class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint==null || constraint.length()==0){
                results.values = list;
                results.count = list.size();
            }else {
                List<Book> modelHoadonList = new ArrayList<>();
                for (Book p: bookList){
                    if (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase())){
                        modelHoadonList.add(p);
                    }
                }
                results.values = modelHoadonList;
                results.count = modelHoadonList.size();
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count==0){
                notifyDataSetInvalidated();
            }else {
                bookList = (List<Book>)results.values;
                notifyDataSetChanged();
            }
        }
    }



}
