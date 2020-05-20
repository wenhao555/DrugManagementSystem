package com.example.drugmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class QueryUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_user);
        Button backtomain = findViewById(R.id.backtomainquery);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryUser.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ListView Editaccount = findViewById(R.id.Editaccount);
        ListView Editpassword = findViewById(R.id.Editpassword);
        List<User> users = DataSupport.findAll(User.class);
        List<String> account = new ArrayList<>();
        List<String> password = new ArrayList<>();
        for (User user:users){
            account.add(user.getAccount());
            password.add(user.getPassword());
        }
        ArrayAdapter<String> accountadapter = new ArrayAdapter<String>(QueryUser.this,android.R.layout.simple_list_item_1,account);
        ArrayAdapter<String> passwordadapter = new ArrayAdapter<String>(QueryUser.this,android.R.layout.simple_list_item_1,password);
        Editaccount.setAdapter(accountadapter);
        Editpassword.setAdapter(passwordadapter);
    }
}
