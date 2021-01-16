package com.example.lab1.adapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab1.R;
import com.example.lab1.db.UserDB;

import com.example.lab1.listview.ListViewNguoiDung;
import com.example.lab1.model.User;


import java.util.ArrayList;
import java.util.List;




public class AdapterUser extends BaseAdapter {
    Context context;
    ListViewNguoiDung listViewNguoiDung;
    User user;
    List<User> userList;
    int layout;
    UserDB userDB;
    private LayoutInflater layoutInflater;
    public AdapterUser(Context context,List<User> userList, int layout) {
        this.context = context;
        this.userList = userList;
        this.layout = layout;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }



    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgDeleteUser, imgUpdate, imgUpdatePass;
        EditText edId,edName,edPass, edPhone;
    }
    public void  notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            userDB = new UserDB(context);
            convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user,parent,false);
            viewHolder.edId = convertView.findViewById(R.id.ed_adapterId);
            viewHolder.edName = convertView.findViewById(R.id.ed_adapterName);
            viewHolder.imgDeleteUser = convertView.findViewById(R.id.img_adapterDelete);
            viewHolder.imgDeleteUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userDB.deleteUser(userList.get(position).getTen());
                    User user = userList.get(position);
                    userList.remove(user);
                    notifyDataSetChanged();
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Sửa ở đây !!", Toast.LENGTH_SHORT).show();
                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.updateuser);
                    final EditText edUpdateUserId;
                    edUpdateUserId = dialog.findViewById(R.id.ed_updateUserId);
                    final EditText edUpdateUserName  = dialog.findViewById(R.id.ed_updateUserName);
                    final EditText edUpdateUserPhone = dialog.findViewById(R.id.ed_updateUserrPhone);
                    final EditText edUpdateUserPass = dialog.findViewById(R.id.ed_updateUserPass);
                    final ImageView imgUpdateUser = dialog.findViewById(R.id.img_updateUserUpdate);
                    final ImageView imgUpdateUserLogOut = dialog.findViewById(R.id.img_updateUserLogOut);
                    edUpdateUserId.setEnabled(false);
                    edUpdateUserPass.setEnabled(false);
                    user=userList.get(position);
                    edUpdateUserId.setText(user.getTen());
                    edUpdateUserName.setText(user.getHoten());
                    edUpdateUserPass.setText(user.getPass());
                    edUpdateUserPhone.setText(user.getPhone());

                    imgUpdateUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "update ở đây", Toast.LENGTH_SHORT).show();
                            user = userList.get(position);
                            user.setTen(edUpdateUserId.getText().toString());
                            user.setPhone(edUpdateUserPhone.getText().toString());
                            user.setPass(edUpdateUserPass.getText().toString());
                             user.setHoten(edUpdateUserName.getText().toString());
                            userDB = new UserDB(context);
                            long update = userDB.updateUser(user,userList.get(position).getTen());
                            if(update > 0){
                                Toast.makeText(context, "Update thành công !!", Toast.LENGTH_SHORT).show();
                                userList.set(position,user);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(context, "Không thành công !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    dialog.show();
                }
            });
            viewHolder.imgUpdatePass = convertView.findViewById(R.id.img_adapterUdatePass);
            viewHolder.imgUpdatePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dia = new Dialog(context);
                    dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dia.setContentView(R.layout.updatepassuser);

                    final EditText edUpdatepassPass = dia.findViewById(R.id.ed_updatepassPass);
                    final EditText edUpdatepassPassCheck = dia.findViewById(R.id.ed_updatepassPassCheck);
                    final ImageView imgUpdatepassUpdate = dia.findViewById(R.id.img_updatepassUpdate);

                    imgUpdatepassUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Update Pass ơ đây", Toast.LENGTH_SHORT).show();
                            String updatePass = edUpdatepassPass.getText().toString();
                            String updatePassCheck =edUpdatepassPassCheck.getText().toString();
                            try{
                                int check = updatePass.compareToIgnoreCase(updatePassCheck);
                                if(check != 0){
                                    Toast.makeText(context, "Pass phải trùng nhau !!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }catch (Exception e){
                                Log.e("Lỗi vui lòng kiểm tra", e.toString());
                            }
                            User user = userList.get(position);
                            user.setPass(edUpdatepassPassCheck.getText().toString());
                            userDB = new UserDB(context);
                            long update =userDB.updatePasswordUser(user);
                            if (update == 0){

                                Toast.makeText(context, "Thành công !!", Toast.LENGTH_SHORT).show();
                                userDB.updatePasswordUser(user);
                                notifyDataSetChanged();
                                dia.dismiss();

                            }else {
                                Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dia.show();
                    dia.create();

                    final ImageView imgUpdatepassLogOut = dia.findViewById(R.id.img_updatepassLogOut);
                    imgUpdatepassLogOut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Log Out", Toast.LENGTH_SHORT).show();
                                listViewNguoiDung.finish();
                        }
                    });

                }
            });

            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();

        }

        User user =userList.get(position);
        viewHolder.edId.setText(user.getTen());
        viewHolder.edName.setText(user.getHoten());

        return convertView;
    }
}



