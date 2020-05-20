package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PurchaseOrderEnquiry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order_enquiry);
        Button back = findViewById(R.id.backtomain_enquiry);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseOrderEnquiry.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ListView enquirybd = findViewById(R.id.enquiry_bd);
        List<PurchaseFroms> purchaseFroms = DataSupport.findAll(PurchaseFroms.class);
        OrderAdapter adapter = new OrderAdapter(PurchaseOrderEnquiry.this,R.layout.purchase_from,purchaseFroms);
        enquirybd.setAdapter(adapter);
    }
}
