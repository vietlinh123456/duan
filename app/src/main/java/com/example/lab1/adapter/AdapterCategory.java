package com.example.lab1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.R;
import com.example.lab1.db.CategoryDB;
import com.example.lab1.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends BaseAdapter {
    Context context;
    List<Category> categoryList;
    CategoryDB categoryDB;
    int layout;
    private LayoutInflater layoutInflater;
    Category category;
    public AdapterCategory(Context context, List<Category> categoryList,int layout){
        this.context = context;
        this.categoryList = categoryList;
        this.layout = layout;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        categoryDB = new CategoryDB(context);
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptercategory,parent,false);
        EditText edAdapterIdCategory = (EditText)convertView.findViewById(R.id.ed_adapterIdCategory);
        EditText edAdapterNameCategory = (EditText) convertView.findViewById(R.id.ed_adapterNameCategory);
        ImageView imgAdapterDeleteCategory = (ImageView) convertView.findViewById(R.id.img_adapterDeleteCategory);
        category = categoryList.get(position);
        imgAdapterDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDB.deleteCategory(categoryList.get(position).getMa());
                Category category = categoryList.get(position);
                categoryList.remove(category);
                notifyDataSetChanged();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Dialog dialog = new Dialog(context);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.updatecategory);
//                final EditText edMa = dialog.findViewById(R.id.ed_updatecategoryMa);
//                final EditText edTen = dialog.findViewById(R.id.ed_updatecategoryTenTL);
//                final EditText edMoTa = dialog.findViewById(R.id.ed_updatecategoryMoTa);
//                final EditText edViTri = dialog.findViewById(R.id.ed_updatecategoryViTri);
//                final ImageView imgUpdate = dialog.findViewById(R.id.img_updatecategoryUpdate);
//                edMa.setEnabled(false);
//                category = categoryList.get(position);
//                edMa.setText(category.getMa());
//                edTen.setText(category.getTen());
//                edMoTa.setText(category.getMoTa());
//                edViTri.setText(category.getViTri());
//                imgUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        category = categoryList.get(position);
//                        category.setMa(edMa.getText().toString());
//                        category.setTen(edTen.getText().toString());
//                        category.setMoTa(edMoTa.getText().toString());
//                        category.setViTri(edViTri.getText().toString());
//                        categoryDB = new CategoryDB(context);
//                        long update = categoryDB.updateCategory(category,categoryList.get(position).getMa());
//                        if (update > 0){
//                            Toast.makeText(context, "Update thành công !!", Toast.LENGTH_SHORT).show();
//                            categoryList.set(position,category);
//                            notifyDataSetChanged();
//                            dialog.dismiss();
//                        }else {
//                            Toast.makeText(context, "Update Không thành công !!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                dialog.show();
            }
        });

        category = categoryList.get(position);
        edAdapterIdCategory.setText(category.getMa());
        edAdapterNameCategory.setText(category.getTen());
        return convertView;
    }
}
