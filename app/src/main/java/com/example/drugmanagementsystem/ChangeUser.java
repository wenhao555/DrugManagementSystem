package com.example.drugmanagementsystem;

import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ChangeUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        Button agree = findViewById(R.id.agree);
        Button disagree = findViewById(R.id.disagree);
        Button back = findViewById(R.id.backtomainchange);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText caccount = findViewById(R.id.caccount);
                String account = caccount.getText().toString();
                EditText opassword = findViewById(R.id.ocpassword);
                String oldpassword = opassword.getText().toString();
                EditText npassword = findViewById(R.id.ncpassword);
                String newpassword = npassword.getText().toString();
                List<User> users = DataSupport.findAll(User.class);
                List <User> userAccount = DataSupport.where("account = ? and password = ?",account,oldpassword).find(User.class);
                if (userAccount.size()>0){
                    for (User user:users){
                        if (user.getAccount().equals(account)&&user.getPassword().equals(oldpassword)){
                            User user1 = new User();
                            user.setPassword(newpassword);
                            user.updateAll("account = ? and password = ?",account,oldpassword);
                            Toast.makeText(ChangeUser.this,"密码修改成功，请重新登录",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangeUser.this,LoginActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }else{
                    Toast.makeText(ChangeUser.this,"请输入正确的用户名和原密码",Toast.LENGTH_SHORT).show();
                }

            }
        });
        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText caccount = findViewById(R.id.caccount);
                EditText opassword = findViewById(R.id.ocpassword);
                EditText npassword = findViewById(R.id.ncpassword);
                caccount.setText(null);
                opassword.setText(null);
                npassword .setText(null);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeUser.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
