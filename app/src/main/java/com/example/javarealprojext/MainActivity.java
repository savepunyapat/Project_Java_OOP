package com.example.javarealprojext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    Button btnBack;
    EditText editName;
    EditText editId;
    FirebaseDatabase fb;
    DatabaseReference dr;
    TextView txtHello;
    ArrayList<String> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dr = FirebaseDatabase.getInstance().getReference();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String id = editId.getText().toString();
                if(editName.length() == 0 || editId.length() == 0){
                    addToast("Unfinish");
                }else{
                    AddMember(name,id);
                    txtHello.setText("Added Member : " + name + " " + id);
                    addToast("finish");
                    editId.setText("");
                    editName.setText("");
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });

    }
    private void init(){
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBackMember);
        editId = (EditText) findViewById(R.id.editId);
        editName = (EditText) findViewById(R.id.editName);
        txtHello = (TextView) findViewById(R.id.txtHello);
    }
    private void AddMember(String name,String id){
        userList = new ArrayList<String>();
        User user = new User(name,id);
        userList.add(name + id);
        dr.child("Users").child("Member").child(name).setValue(userList);
    }
    private void addToast(String text){
        Toast.makeText(this, text,Toast.LENGTH_LONG).show();
    }
}