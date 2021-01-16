package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ImageView imgPrev,imgSignIn,imgCancel,imgPrevLogin;
    CheckBox chkLogin;
    EditText edLoginName,edLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imgPrev = findViewById(R.id.img_prev);
        imgSignIn = findViewById(R.id.img_sginIn);
        imgCancel = findViewById(R.id.img_cancel);
        imgPrevLogin = findViewById(R.id.img_prevLogin);
        edLoginName = findViewById(R.id.ed_loginName);
        edLoginPass = findViewById(R.id.ed_LoginPass);
        chkLogin = findViewById(R.id.chk_checkLogin);

        chkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edName = edLoginName.getText().toString();
                String edPass = edLoginPass.getText().toString();
                if(edName.equalsIgnoreCase("") || edPass.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Vui lòng điền thông tin User và Pass !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Check Okay !!", Toast.LENGTH_SHORT).show();
            }
        });


        imgPrevLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        imgSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edName = edLoginName.getText().toString();
                String edPass = edLoginPass.getText().toString();
                if(edName.equalsIgnoreCase("") || edPass.equalsIgnoreCase("")){
                    Toast.makeText(LoginActivity.this, "Vui lòng điền thông tin User và Pass !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Login successfull !!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,QuanLySach.class);
                startActivity(intent);
            }
        });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}