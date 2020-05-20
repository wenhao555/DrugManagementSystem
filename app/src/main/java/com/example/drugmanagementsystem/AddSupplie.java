package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class AddSupplie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplie);
        Button backtomain = findViewById(R.id.backtomainaddsupplie);
        Button submit = findViewById(R.id.submit_supplie);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText gysbhEdit = findViewById(R.id.gysbh);
                EditText gysmcEdit = findViewById(R.id.gysmc);
                EditText gysdhEdit = findViewById(R.id.gysdh);
                String gysbh = gysbhEdit.getText().toString();
                String gysmc = gysmcEdit.getText().toString();
                String gysdh = gysdhEdit.getText().toString();
                List<Supplie> supplies1 = DataSupport.where("gysbh = ?",gysbh).find(Supplie.class);
                List<Supplie> supplies2 = DataSupport.where("gysmc = ?",gysmc).find(Supplie.class);
                if (gysbh.equals("")||gysmc.equals("")||gysdh.equals("")){
                    Toast.makeText(AddSupplie.this,"供应商编号、名称和号码都不能为空，请查证后再添加",Toast.LENGTH_SHORT).show();
                }else {
                    if (supplies1.size()>0||supplies2.size()>0){
                        if (supplies1.size()>0&&supplies2.size()>0){
                            Toast.makeText(AddSupplie.this,"该供应商已存在",Toast.LENGTH_SHORT).show();
                        }else if (supplies1.size()>0&&supplies2.size()==0){
                            Toast.makeText(AddSupplie.this,"供应商编号："+gysbh+"已存在",Toast.LENGTH_SHORT).show();
                        }else if (supplies1.size()==0&&supplies2.size()>0){
                            Toast.makeText(AddSupplie.this,"供应商名称："+gysmc+"已存在",Toast.LENGTH_SHORT).show();
                        }
                    }else if (supplies1.size()<1&&supplies2.size()<1){
                        Supplie supplie = new Supplie();
                        supplie.setGysbh(gysbh);
                        supplie.setGysmc(gysmc);
                        supplie.setGysdh(gysdh);
                        supplie.save();
                        Toast.makeText(AddSupplie.this,"供应商添加成功",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSupplie.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
