package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SellOrderEnquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_order_enquiry);
        Button back = (Button)findViewById(R.id.backtomainsellorderenquiry);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellOrderEnquiry.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ListView enquirybd = findViewById(R.id.enquiry_bd_sell);
        List<SalesFroms> salesFroms = DataSupport.findAll(SalesFroms.class);
        SaleOrderAdapter adapter = new SaleOrderAdapter(SellOrderEnquiry.this,R.layout.sellorderbd,salesFroms);
        enquirybd.setAdapter(adapter);
    }
}
