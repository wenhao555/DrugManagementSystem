package com.example.drugmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button purchaseControl = (Button)findViewById(R.id.purchase_control);
        Button userManage = (Button)findViewById(R.id.user_manage);
        Button sellControl = (Button)findViewById(R.id.sell_control);
        Button drugDestroy =(Button) findViewById(R.id.drug_destroy);
        Button exit = (Button)findViewById(R.id.exit);
        purchaseControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PurchaseControl.class);
                startActivity(intent);
            }
        });
        userManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserManage.class);
                startActivity(intent);
            }
        });
        sellControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SellControl.class);
                startActivity(intent);
            }
        });
        drugDestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DrugDestroy.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);
                alertdialogbuilder.setMessage("确定要退出程序吗？");
                alertdialogbuilder.setPositiveButton("确定",click1);
                alertdialogbuilder.setNegativeButton("取消",click2);
                AlertDialog alertDialog1 = alertdialogbuilder.create();
                alertDialog1.show();
            }
            private DialogInterface.OnClickListener click1 = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            };
            private DialogInterface.OnClickListener click2 = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        });


    }
}
