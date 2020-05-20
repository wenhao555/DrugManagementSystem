package com.example.drugmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellControl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_control);
        Button back = (Button)findViewById(R.id.backtomainsellcontrol);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellControl.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button sell = findViewById(R.id.sell);
        Button changePrice = findViewById(R.id.change_price);
        Button sellOrderEnquiry = findViewById(R.id.sell_order_enquiry);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellControl.this,SellGoods.class);
                startActivity(intent);
            }
        });
        changePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellControl.this,ChangePrice.class);
                startActivity(intent);
            }
        });
        sellOrderEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellControl.this,SellOrderEnquiry.class);
                startActivity(intent);
            }
        });


    }

}
