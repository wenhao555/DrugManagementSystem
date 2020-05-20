package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ChangePrice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_price);
        Button back = (Button)findViewById(R.id.backtomainchangeprice);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePrice.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button submitYpbh = findViewById(R.id.submit_ypbh);
        submitYpbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ypbhEdit = findViewById(R.id.ypbh_changeprice);
                if (ypbhEdit.equals("")||ypbhEdit==null){
                    Toast.makeText(ChangePrice.this,"药品编号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    String ypbh = ypbhEdit.getText().toString();
                    List<Drug> drugs = DataSupport.findAll(Drug.class);
                    List<Drug> drugsbh = DataSupport.where("ypbh = ?",ypbh).find(Drug.class);
                    if (drugsbh.size()>0){
                        for (Drug drug:drugs){
                            if (drug.getYpbh().equals(ypbh)){
                                TextView ypxx = findViewById(R.id.ypxx);
                                ypxx.setText("药品编号:"+drug.getYpbh()+"名称:"+drug.getYpmc()+"售价:"+drug.getYpsj()+"进价:"+drug.getYpjj());

                            }
                        }
                    }else {
                        Toast.makeText(ChangePrice.this,"药品编号输入错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button tjjj = findViewById(R.id.tjjj);
        tjjj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ypxx = findViewById(R.id.ypxx);
                if (ypxx.equals("")){
                    Toast.makeText(ChangePrice.this,"请先输入药品编号",Toast.LENGTH_SHORT).show();
                }else {
                    EditText setjPriceEdit = findViewById(R.id.setjprice);
                    if (setjPriceEdit.equals("")||setjPriceEdit.length()==0){
                        Toast.makeText(ChangePrice.this,"请输入正确的进价",Toast.LENGTH_SHORT).show();
                    }else {
                        EditText ypbhEdit = findViewById(R.id.ypbh_changeprice);
                        String ypbh = ypbhEdit.getText().toString();
                        double setjPrice = Double.parseDouble(setjPriceEdit.getText().toString());
                        List<Drug> drugs = DataSupport.findAll(Drug.class);
                        for (Drug drug:drugs){
                            if (drug.getYpbh().equals(ypbh)){
                                drug.setYpjj(setjPrice);
                                drug.updateAll("ypbh = ?",ypbh);
                                Toast.makeText(ChangePrice.this,"药品进价修改成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

        Button tjsj = findViewById(R.id.tjsj);
        tjsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ypxx = findViewById(R.id.ypxx);
                if (ypxx.equals("")){
                    Toast.makeText(ChangePrice.this,"请先输入药品编号",Toast.LENGTH_SHORT).show();
                }else {
                    EditText setsPriceEdit = findViewById(R.id.setsprice);
                    if (setsPriceEdit.length()==0||setsPriceEdit.equals("")){
                        Toast.makeText(ChangePrice.this,"请输入正确的售价",Toast.LENGTH_SHORT).show();
                    }else {
                        EditText ypbhEdit = findViewById(R.id.ypbh_changeprice);
                        String ypbh = ypbhEdit.getText().toString();
                        double setsPrice = Double.parseDouble(setsPriceEdit.getText().toString());
                        List<Drug> drugs = DataSupport.findAll(Drug.class);
                        for (Drug drug:drugs){
                            if (drug.getYpbh().equals(ypbh)){
                                drug.setYpjj(setsPrice);
                                drug.updateAll("ypbh = ?",ypbh);
                                Toast.makeText(ChangePrice.this,"药品售价修改成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

    }
}
