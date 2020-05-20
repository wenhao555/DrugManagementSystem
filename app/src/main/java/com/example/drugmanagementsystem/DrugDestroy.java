package com.example.drugmanagementsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class DrugDestroy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_destroy);
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        Button submitDestory = findViewById(R.id.submit_destory);
        submitDestory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ypbhEdit = findViewById(R.id.ypbh_destory);
                EditText xhslEdit = findViewById(R.id.xhsl_destory);
                if (ypbhEdit.equals("")||xhslEdit.equals("")||xhslEdit.length()==0){
                    Toast.makeText(DrugDestroy.this,"销毁药品编号和数量不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    List<Stock> stocks = DataSupport.findAll(Stock.class);
                    int xhsl = Integer.parseInt(xhslEdit.getText().toString());
                    String ypbh = ypbhEdit.getText().toString();
                    for (Stock stock:stocks){
                        if (stock.getYpbh().equals(ypbh)){
                            if (stock.getYpsl()>=xhsl){
                                stock.setYpsl(stock.getYpsl()-xhsl);
                                stock.updateAll("ypbh = ?",ypbh);
                                Toast.makeText(DrugDestroy.this,"销毁药品:"+stock.getYpbh()+"数量:"+xhsl+"成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(DrugDestroy.this,"库存量小于销毁数量",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
    }

}
