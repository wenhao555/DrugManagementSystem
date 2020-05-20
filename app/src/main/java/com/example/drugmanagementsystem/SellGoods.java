package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SellGoods extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_goods);
        Button back = (Button)findViewById(R.id.backtomainsellgoods);
        Button jrqd = findViewById(R.id.jrqd_sellgoods);
        Button qcqd = findViewById(R.id.qcqd_sellgoods);
        Button czqd = findViewById(R.id.czqd_sellgoods);
        Button submitGoods = findViewById(R.id.submit_goods_sellgoods);
        submitGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SalesFrom> salesFroms = DataSupport.findAll(SalesFrom.class);
                if (salesFroms.size()==0){
                    Toast.makeText(SellGoods.this,"请先添加清单",Toast.LENGTH_SHORT).show();
                }else{
                    double sum = 0;

                    List<Stock> stocks = DataSupport.findAll(Stock.class);
                    List<Drug> drugs = DataSupport.findAll(Drug.class);
                    TextView xse = findViewById(R.id.xse);
                    for (SalesFrom salesFrom:salesFroms){

                        SalesFroms salesFroms1 = new SalesFroms();
                        salesFroms1.setYpbh(salesFrom.getYpbh());
                        salesFroms1.setXssl(salesFrom.getXssl());
                        salesFroms1.save();

                        for (Stock stock:stocks){
                            if (salesFrom.getYpbh().equals(stock.getYpbh())){
                                stock.setYpsl(stock.getYpsl()-salesFrom.getXssl());
                                stock.updateAll("ypbh = ?",stock.getYpbh());
                            }
                        }
                    }
                    SalesFroms salesFroms1 = new SalesFroms();
                    salesFroms1.setYpbh("0000");
                    salesFroms1.setXssl(0);
                    salesFroms1.save();
                    for (SalesFrom salesFrom:salesFroms){
                        for (Drug drug:drugs){
                            if (salesFrom.getYpbh().equals(drug.getYpbh())){
                                sum = sum+salesFrom.getXssl()*drug.getYpsj();
                            }
                        }
                    }
                    xse.setText("总价格为："+sum+"元。");

                    DataSupport.deleteAll(SalesFrom.class);
                }
            }
        });
        czqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(SalesFrom.class);
                EditText ypbhEdit = findViewById(R.id.ypbh_sellgoods);
                EditText xsslEdit = findViewById(R.id.xssl_sellgoods);
                TextView xse = findViewById(R.id.xse);
                xse.setText(null);
                ypbhEdit.setText(null);
                xsslEdit.setText(null);
                Toast.makeText(SellGoods.this,"重置清单成功",Toast.LENGTH_SHORT).show();
                List<SalesFrom> salesFroms = DataSupport.findAll(SalesFrom.class);
                ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms);
                goodsOrder.setAdapter(adapter);
            }
        });
        qcqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView xse = findViewById(R.id.xse);
                xse.setText(null);
                EditText ypbhEdit = findViewById(R.id.ypbh_sellgoods);
                EditText xsslEdit = findViewById(R.id.xssl_sellgoods);
                if (ypbhEdit.equals("")||xsslEdit.equals("")||ypbhEdit==null||ypbhEdit==null||xsslEdit.length()==0){
                    List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                    ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                    SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                    goodsOrder.setAdapter(adapter);
                    Toast.makeText(SellGoods.this,"药品编号或数量不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    String ypbh = ypbhEdit.getText().toString();
                    List<Stock> stockypbh = DataSupport.where("ypbh = ?",ypbh).find(Stock.class);
                    if (stockypbh.size()>0){
                        int xssl = Integer.parseInt(xsslEdit.getText().toString());
                        List<SalesFrom> salesFromsypbh = DataSupport.where("ypbh = ?",ypbh).find(SalesFrom.class);
                        if (xssl>0){
                            if (salesFromsypbh.size()<1){
                                List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                                ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                                goodsOrder.setAdapter(adapter);
                                Toast.makeText(SellGoods.this,"清单中没有该物品",Toast.LENGTH_SHORT).show();
                            }else {
                                List<SalesFrom> salesFroms = DataSupport.findAll(SalesFrom.class);
                                for (SalesFrom salesFrom:salesFroms){
                                    if (salesFrom.getYpbh().equals(ypbh)){
                                        if (salesFrom.getXssl()>=xssl){
                                            salesFrom.setXssl(salesFrom.getXssl()-xssl);
                                            salesFrom.save();
                                            DataSupport.deleteAll(SalesFrom.class,"xssl = ?","0");
                                            Toast.makeText(SellGoods.this,"移除清单成功",Toast.LENGTH_SHORT).show();
                                            List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                                            ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                            SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                                            goodsOrder.setAdapter(adapter);
                                            break;
                                        }else {
                                            List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                                            ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                            SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                                            goodsOrder.setAdapter(adapter);
                                            Toast.makeText(SellGoods.this,"清单药品不足",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }else {
                            List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                            ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                            SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                            goodsOrder.setAdapter(adapter);
                            Toast.makeText(SellGoods.this,"药品数量不能小于0",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                        ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                        SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                        goodsOrder.setAdapter(adapter);
                        Toast.makeText(SellGoods.this,"库存内没有该药品",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        jrqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView xse = findViewById(R.id.xse);
                xse.setText(null);
                EditText ypbhEdit = findViewById(R.id.ypbh_sellgoods);
                EditText xsslEdit = findViewById(R.id.xssl_sellgoods);
                if (ypbhEdit.equals("")||xsslEdit.equals("")||ypbhEdit==null||ypbhEdit==null||xsslEdit.length()==0){
                    List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                    ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                    SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                    goodsOrder.setAdapter(adapter);
                    Toast.makeText(SellGoods.this,"药品编号或数量不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    String ypbh = ypbhEdit.getText().toString();
                    List<Stock> stockypbh = DataSupport.where("ypbh = ?",ypbh).find(Stock.class);
                    if (stockypbh.size()>0){
                        int xssl = Integer.parseInt(xsslEdit.getText().toString());
                        List<Stock> stocks = DataSupport.findAll(Stock.class);
                        List<SalesFrom> salesFromsypbh = DataSupport.where("ypbh = ?",ypbh).find(SalesFrom.class);
                        if (xssl>0){
                            if (salesFromsypbh.size()<1){
                                for (Stock stock:stocks){
                                    if (stock.getYpbh().equals(ypbh)){
                                        if (stock.getYpsl()>=xssl){
                                            SalesFrom salesFrom = new SalesFrom();
                                            salesFrom.setYpbh(ypbh);
                                            salesFrom.setXssl(xssl);
                                            salesFrom.save();
                                            Toast.makeText(SellGoods.this,"添加清单成功",Toast.LENGTH_SHORT).show();
                                            List<SalesFrom> salesFroms = DataSupport.findAll(SalesFrom.class);
                                            ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                            SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms);
                                            goodsOrder.setAdapter(adapter);
                                            break;
                                        }else {
                                            List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                                            ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                            SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                                            goodsOrder.setAdapter(adapter);
                                            Toast.makeText(SellGoods.this,"药品库存不足",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }else {
                                List<SalesFrom> salesFroms = DataSupport.findAll(SalesFrom.class);
                                for (SalesFrom salesFrom:salesFroms){
                                    if (salesFrom.getYpbh().equals(ypbh)){
                                        for (Stock stock:stocks){
                                            if (stock.getYpbh().equals(ypbh)){
                                                if (salesFrom.getXssl()+xssl>stock.getYpsl()){
                                                    List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                                                    ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                                    SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                                                    goodsOrder.setAdapter(adapter);
                                                    Toast.makeText(SellGoods.this,"药品库存不足",Toast.LENGTH_SHORT).show();
                                                    break;
                                                }else {
                                                    salesFrom.setXssl(salesFrom.getXssl()+xssl);
                                                    salesFrom.updateAll("ypbh = ?",ypbh);
                                                    Toast.makeText(SellGoods.this,"添加清单成功",Toast.LENGTH_SHORT).show();
                                                    List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                                                    ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                                                    SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                                                    goodsOrder.setAdapter(adapter);
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }else {
                            List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                            ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                            SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                            goodsOrder.setAdapter(adapter);
                            Toast.makeText(SellGoods.this,"药品数量不能小于0",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        List<SalesFrom> salesFroms1 = DataSupport.findAll(SalesFrom.class);
                        ListView goodsOrder = findViewById(R.id.goods_order_sellgoods);
                        SellFromAdapter adapter = new SellFromAdapter(SellGoods.this,R.layout.sell_from,salesFroms1);
                        goodsOrder.setAdapter(adapter);
                        Toast.makeText(SellGoods.this,"库存内没有该药品",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellGoods.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
