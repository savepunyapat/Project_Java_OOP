package com.example.SCMusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class MenuActivity extends AppCompatActivity {
    Button btnUser;
    Button btnShow;
    Button btnLogin;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        init();
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void init(){
        btnUser = (Button) findViewById(R.id.btnUser);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnLogin = (Button) findViewById(R.id.btnGoLogin);
    }
}
