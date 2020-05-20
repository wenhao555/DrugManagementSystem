package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class StockGoods extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_goods);
        Button back = findViewById(R.id.backtomain_stockgoods);
        Button submitGoods = findViewById(R.id.submit_goods);
        Button jrqd = findViewById(R.id.jrqd);
        Button qucd = findViewById(R.id.qcqd);
        Button czqd = findViewById(R.id.czqd);
        jrqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PurchaseFrom> purchase = DataSupport.findAll(PurchaseFrom.class);
                if (purchase.size()==0){
                    PurchaseFrom purchaseFrom2 = new PurchaseFrom();
                    purchaseFrom2.setYpbh("0");
                    purchaseFrom2.setCgsl(0);
                    purchaseFrom2.save();
                }
                EditText ypbhEdit = findViewById(R.id.ypbh_stockgoods);
                EditText cgslEdit = findViewById(R.id.cgsl);
                ListView cgbd = findViewById(R.id.goods_order);
                String ypbh = ypbhEdit.getText().toString();
                List<Drug> drugs = DataSupport.where("ypbh = ?",ypbh).find(Drug.class);
                if (ypbhEdit.equals("")||cgslEdit.equals("")||cgslEdit.length()==0||ypbhEdit.length()==0){
                    Toast.makeText(StockGoods.this,"药品编号和数量均不能为空",Toast.LENGTH_SHORT).show();
                }else if(drugs.size()<1){
                    Toast.makeText(StockGoods.this,"药品编号不存在，请查证后输入",Toast.LENGTH_SHORT).show();
                    ypbhEdit.setText(null);
                }else if (Integer.parseInt(cgslEdit.getText().toString())<0){
                    Toast.makeText(StockGoods.this,"采购数量不能低于0",Toast.LENGTH_SHORT).show();
                }else if ((drugs.size()>0&&Integer.parseInt(cgslEdit.getText().toString())>0)){
                    List<PurchaseFrom> purchaseFroms = DataSupport.findAll(PurchaseFrom.class);
                    for (PurchaseFrom purchaseFrom:purchaseFroms){
                       if (purchaseFrom.getYpbh().equals(ypbh)){
                            purchaseFrom.setCgsl(purchaseFrom.getCgsl()+Integer.parseInt(cgslEdit.getText().toString()));
                            purchaseFrom.updateAll("ypbh = ?",ypbh);
                            Toast.makeText(StockGoods.this,"添加清单成功",Toast.LENGTH_SHORT).show();
                            break;
                        }else  if (purchaseFrom.getYpbh().equals(DataSupport.findLast(PurchaseFrom.class).getYpbh())){
                           PurchaseFrom purchaseFrom1 = new PurchaseFrom();
                           purchaseFrom1.setYpbh(ypbh);
                           purchaseFrom1.setCgsl(Integer.parseInt(cgslEdit.getText().toString()));
                           purchaseFrom1.save();
                           Toast.makeText(StockGoods.this,"添加清单成功",Toast.LENGTH_SHORT).show();
                           DataSupport.deleteAll(PurchaseFrom.class,"ypbh = ?","0");
                       }
                    }
                    List<PurchaseFrom> purchaseFroms1 = DataSupport.findAll(PurchaseFrom.class);
                    PurchaseFromAdapter adapter = new PurchaseFromAdapter(StockGoods.this,R.layout.purchase_from,purchaseFroms1);
                    cgbd.setAdapter(adapter);
                }
            }
        });
        qucd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ypbhEdit = findViewById(R.id.ypbh_stockgoods);
                EditText cgslEdit = findViewById(R.id.cgsl);
                ListView cgbd = findViewById(R.id.goods_order);
                String ypbh = ypbhEdit.getText().toString();
                List<Drug> drugs = DataSupport.where("ypbh = ?",ypbh).find(Drug.class);

                if (ypbhEdit.equals("")||cgslEdit.equals("")||cgslEdit.length()==0){
                    Toast.makeText(StockGoods.this,"药品编号和数量均不能为空",Toast.LENGTH_SHORT).show();
                }else if (Integer.parseInt(cgslEdit.getText().toString())<0){
                    Toast.makeText(StockGoods.this,"移除数量不能低于0",Toast.LENGTH_SHORT).show();
                }else if(drugs.size()<1){
                    Toast.makeText(StockGoods.this,"药品编号不存在，请查证后输入",Toast.LENGTH_SHORT).show();
                    ypbhEdit.setText(null);
                }else if ((drugs.size()>0&&Integer.parseInt(cgslEdit.getText().toString())>0)){
                    List<PurchaseFrom> purchaseFroms = DataSupport.findAll(PurchaseFrom.class);
                    for (PurchaseFrom purchaseFrom:purchaseFroms){
                        if (purchaseFrom.getYpbh().equals(ypbh)&&purchaseFrom.getCgsl()>=Integer.parseInt(cgslEdit.getText().toString())){
                            if (purchaseFrom.getCgsl()==Integer.parseInt(cgslEdit.getText().toString())){
                                DataSupport.deleteAll(PurchaseFrom.class,"ypbh = ?",purchaseFrom.getYpbh());
                            }
                            PurchaseFrom purchaseFrom1 = new PurchaseFrom();
                            purchaseFrom1.setCgsl(purchaseFrom.getCgsl()-Integer.parseInt(cgslEdit.getText().toString()));
                            purchaseFrom1.updateAll("ypbh = ?",ypbh);
                            Toast.makeText(StockGoods.this,"移除清单成功",Toast.LENGTH_SHORT).show();
                        }else if (purchaseFrom.getCgsl()<Integer.parseInt(cgslEdit.getText().toString())){
                            Toast.makeText(StockGoods.this,"药品数量错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                    List<PurchaseFrom>purchaseFroms1 = DataSupport.findAll(PurchaseFrom.class);
                    PurchaseFromAdapter adapter = new PurchaseFromAdapter(StockGoods.this,R.layout.purchase_from,purchaseFroms1);
                    cgbd.setAdapter(adapter);
                }
            }
        });
        czqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListView cgbd = findViewById(R.id.goods_order);
                DataSupport.deleteAll(PurchaseFrom.class);
                List<PurchaseFrom> purchaseFroms = DataSupport.findAll(PurchaseFrom.class);
                PurchaseFromAdapter adapter = new PurchaseFromAdapter(StockGoods.this,R.layout.purchase_from,purchaseFroms);
                cgbd.setAdapter(adapter);
            }
        });
        submitGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PurchaseFrom> purchaseFroms = DataSupport.findAll(PurchaseFrom.class);
                List<Stock> stocks = DataSupport.findAll(Stock.class);
                if (stocks.size()==0){
                    Stock stock = new Stock();
                    stock.setYpbh("0");
                    stock.setYpsl(0);
                    stock.save();
                }
                for(PurchaseFrom purchaseFrom: purchaseFroms){
                    for (Stock stock:stocks){
                        if (purchaseFrom.getYpbh().equals(stock.getYpbh())){
                            stock.setYpsl(stock.getYpsl()+purchaseFrom.getCgsl());
                            stock.updateAll("ypbh = ?",stock.getYpbh());
                            break;
                        }else if (purchaseFrom.getYpbh().equals(DataSupport.findLast(PurchaseFrom.class).getYpbh())&&stock.getYpbh().equals(DataSupport.findLast(Stock.class).getYpbh())){
                            Stock stock1 = new Stock();
                            stock1.setYpbh(purchaseFrom.getYpbh());
                            stock1.setYpsl(purchaseFrom.getCgsl());
                            stock1.save();
                            DataSupport.deleteAll(Stock.class,"ypbh = ?","0");
                        }
                    }
                }
//                PurchaseFroms purchaseFroms1 = new PurchaseFroms();
//                List ypbh = new ArrayList();
//                List cgsl = new ArrayList();
//                for (PurchaseFrom purchaseFrom:purchaseFroms){
//                    ypbh.add(purchaseFrom.getYpbh());
//                    cgsl.add(purchaseFrom.getCgsl());
//                }
//                purchaseFroms1.setYpbh(ypbh);
//                purchaseFroms1.setCgsl(cgsl);
//                purchaseFroms1.save();
                List<PurchaseFrom> purchase = DataSupport.findAll(PurchaseFrom.class);
                if (purchase.size()>0){
                    for (PurchaseFrom purchaseFrom:purchase){
                        PurchaseFroms purchaseFroms1 = new PurchaseFroms();
                        purchaseFroms1.setYpbh(purchaseFrom.getYpbh());
                        purchaseFroms1.setCgsl(purchaseFrom.getCgsl());
                        purchaseFroms1.save();
                    }
                    PurchaseFroms purchaseFroms1 = new PurchaseFroms();
                    purchaseFroms1.setYpbh("0000");
                    purchaseFroms1.setCgsl(0);
                    purchaseFroms1.save();
                    Toast.makeText(StockGoods.this,"采购成功",Toast.LENGTH_SHORT).show();
                    List<PurchaseFroms> purchaseFromsList = DataSupport.findAll(PurchaseFroms.class);
                    DataSupport.deleteAll(PurchaseFrom.class);
                    List<PurchaseFrom> purchaseFrom = DataSupport.findAll(PurchaseFrom.class);
                    ListView cgbd = findViewById(R.id.goods_order);
                    PurchaseFromAdapter adapter = new PurchaseFromAdapter(StockGoods.this,R.layout.purchase_from,purchaseFrom);
                    cgbd.setAdapter(adapter);
                }else{
                    Toast.makeText(StockGoods.this,"请先添加采购药品",Toast.LENGTH_SHORT).show();
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StockGoods.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
