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

public class AddDrug extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);
        Button submit = findViewById(R.id.submit_drug);
        Button backtomainadddrug = findViewById(R.id.backtomainadddrug);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ypbhEdit = findViewById(R.id.ypbh);
                EditText ypmcEdit = findViewById(R.id.ypmc);
                EditText ypsjEdit = findViewById(R.id.ypsj);
                EditText ypjjEdit = findViewById(R.id.ypjj);
                EditText gysbhEdit = findViewById(R.id.gysbh_drug);
                String ypbh = ypbhEdit.getText().toString();
                String ypmc = ypmcEdit.getText().toString();
                if (ypjjEdit.equals("")||ypsjEdit.equals("")||ypjjEdit.length()==0||ypsjEdit.length()==0){
                    Toast.makeText(AddDrug.this,"价格不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    String  ypjj1 = ypjjEdit.getText().toString();
                    String ypsj1 = ypsjEdit.getText().toString();
                    String gysbh = gysbhEdit.getText().toString();
                    List<Drug> drugbh = DataSupport.where("ypbh = ?",ypbh).find(Drug.class);
                    List<Drug> drugmc = DataSupport.where("ypmc = ?",ypmc).find(Drug.class);
                    List<Supplie> druggys = DataSupport.where("gysbh = ?",gysbh).find(Supplie.class);
                    if (ypbh.equals("")||ypmc.equals("")||gysbh.equals("")){
                        Toast.makeText(AddDrug.this,"药品编号、名称和供货商不能为空",Toast.LENGTH_SHORT).show();
                    }else if (drugbh.size()>0||drugmc.size()>0){
                        Toast.makeText(AddDrug.this,"药品编号或名称已存在",Toast.LENGTH_SHORT).show();
                    }else if (druggys.size()<1){
                        Toast.makeText(AddDrug.this,"供应商不存在，请先添加供应商",Toast.LENGTH_SHORT).show();
                    }else if(drugbh.size()<1&&drugmc.size()<1&&druggys.size()>0){
                        Drug drug = new Drug();
                        double ypjj = Double.parseDouble(ypjj1);
                        double ypsj = Double.parseDouble(ypsj1);
                        drug.setYpbh(ypbh);
                        drug.setYpmc(ypmc);
                        drug.setYpjj(ypjj);
                        drug.setYpsj(ypsj);
                        drug.setGysbh(gysbh);
                        drug.save();
                        Toast.makeText(AddDrug.this,"药品"+ypmc+"添加成功",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        backtomainadddrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDrug.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
