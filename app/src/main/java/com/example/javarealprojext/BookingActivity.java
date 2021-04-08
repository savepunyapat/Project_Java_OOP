package com.example.javarealprojext;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends Activity {
    private int datePost;
    private int monthPost;
    private TextView searchR;
    private Spinner dateSpinner;
    private Spinner monthSpinner;
    private Button  searchButton;


    private ArrayList<String> dateSelect = new ArrayList<String>();
    private ArrayList<String> monthSelect = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_search);
        init();
        createDate();
        SpinnerAndSearch();



    }
    private void createDate(){
        String dateText;
        for(int i = 1;i<=31;i++){
            dateText = String.valueOf(i);
            dateSelect.add(dateText);
        }
        monthSelect.add("January");
        monthSelect.add("Febuary");
        monthSelect.add("March");
        monthSelect.add("April");
        monthSelect.add("May");
        monthSelect.add("June");
        monthSelect.add("July");
        monthSelect.add("August");
        monthSelect.add("September");
        monthSelect.add("October");
        monthSelect.add("November");
        monthSelect.add("December");
    }
    private void init(){

        dateSpinner = (Spinner) findViewById(R.id.dateShow);
        monthSpinner = (Spinner) findViewById(R.id.monthShow);
        searchButton = (Button) findViewById(R.id.BtnSearch);
        searchR = (TextView) findViewById(R.id.searchResult);
    }
    private  void SpinnerAndSearch(){

        ArrayAdapter<String> adapterDate = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, dateSelect);
        dateSpinner.setAdapter(adapterDate);

        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, monthSelect);
        monthSpinner.setAdapter(adapterMonth);
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                datePost = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                monthPost = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String outputSearch = dateSelect.get(datePost) + " " +  monthSelect.get(monthPost)+" 2021";
               searchR.setText(outputSearch);
               Intent intent = new Intent(BookingActivity.this,BookingMenu.class);
               startActivity(intent);

            }
        });
    }

}
