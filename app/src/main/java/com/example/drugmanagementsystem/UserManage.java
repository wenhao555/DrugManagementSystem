package com.example.drugmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);
        Button back = findViewById(R.id.back);
        final Button addUser = findViewById(R.id.add_user);
        Button delectUser = findViewById(R.id.delete_user);
        Button changeUser = findViewById(R.id.change_user);
        Button queryUser = findViewById(R.id.query_user);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManage.this,RegisteActivity.class);
                startActivity(intent);
            }
        });
        delectUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManage.this,DeleteUser.class);
                startActivity(intent);
            }
        });
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManage.this,ChangeUser.class);
                startActivity(intent);
            }
        });
        queryUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManage.this,QueryUser.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManage.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
