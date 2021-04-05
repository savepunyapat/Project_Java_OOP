package com.example.javarealprojext;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<String> UserData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdata);
        UserData = new ArrayList<>();
        User u1 = new User("Tik","10");
        User u2 = new User("Wave", "9");
        UserData.add("save");
        UserData.add("Man");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,UserData);
        listview.setAdapter(arrayAdapter);
    }
    private void init(){

    }
}
