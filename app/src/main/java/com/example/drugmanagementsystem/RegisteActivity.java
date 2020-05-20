package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

public class RegisteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        Button submit = (Button) findViewById(R.id.submit);
        Button back = findViewById(R.id.backtomainregist);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText accountEdit = (EditText) findViewById(R.id.raccount);
                EditText passwordEdit = (EditText) findViewById(R.id.rpassword);
                EditText passwordcheckEdit = (EditText)findViewById(R.id.rpassword_check);
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String passwordcheck = passwordcheckEdit.getText().toString();
                List<User> users = DataSupport.findAll(User.class);
                if (account.equals("")||password.equals("")){
                    Toast.makeText(RegisteActivity.this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    for (User user:users){
                        if (user.getAccount().equals(account)){
                            Toast.makeText(RegisteActivity.this,"用户名已存在!",Toast.LENGTH_LONG).show();
                        }else if (user.getAccount().equals(DataSupport.findLast(User.class).getAccount())){
                            if (password.equals(passwordcheck)){
                                User user1 = new User();
                                user1.setAccount(account.toString());
                                user1.setPassword(password.toString());
                                user1.save();
                                Toast.makeText(RegisteActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisteActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(RegisteActivity.this,"两次密码不相同！请核对",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });
    }
}
