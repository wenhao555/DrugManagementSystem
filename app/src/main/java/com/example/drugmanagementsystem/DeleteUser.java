package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class DeleteUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        Button deleteSubmit = findViewById(R.id.delete_submit);
        Button backToMain = findViewById(R.id.backtomain);
        deleteSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText deleteAccount1 = findViewById(R.id.delete_at);
                String deleteAccount = deleteAccount1.getText().toString();
                List <User> userAccount = DataSupport.where("account = ?",deleteAccount).find(User.class);
                if (userAccount.size()>0){
                    Toast.makeText(DeleteUser.this,"该用户已被删除",Toast.LENGTH_SHORT).show();
                    DataSupport.deleteAll(User.class,"account = ?",deleteAccount);
                }else {
                    Toast.makeText(DeleteUser.this,"没有查找到该用户，请输入正确的账户",Toast.LENGTH_SHORT).show();
                }
            }
        });
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteUser.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
