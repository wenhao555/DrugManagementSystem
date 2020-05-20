package com.example.drugmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;

public class PurchaseControl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_control);
        Button back = findViewById(R.id.back);
        Button addSupplie = findViewById(R.id.add_supplie);
        Button addDrug = findViewById(R.id.add_drug);
        Button stockGoods = findViewById(R.id.stock_goods);
        Button purchaseOrderEnquiry = findViewById(R.id.purchase_order_enquiry);
        addSupplie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseControl.this,AddSupplie.class);
                startActivity(intent);
            }
        });
        addDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseControl.this,AddDrug.class);
                startActivity(intent);
            }
        });
        stockGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(PurchaseFrom.class);
                Intent intent = new Intent(PurchaseControl.this,StockGoods.class);
                startActivity(intent);
            }
        });
        purchaseOrderEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseControl.this,PurchaseOrderEnquiry.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseControl.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
