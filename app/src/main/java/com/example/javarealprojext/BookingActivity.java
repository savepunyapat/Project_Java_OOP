package com.example.javarealprojext;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends Activity {
    private int datePost;
    private int monthPost;
    private LinearLayout shownTable;
    private LinearLayout LayOne;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView st1;
    private TextView st2;
    private TextView st3;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    private TextView searchR;
    private Spinner dateSpinner;
    private Spinner monthSpinner;
    private Button  searchButton;
    String roomStatus[] = {"ว่าง", "ไม่ว่าง"};
    int image[] = {R.drawable.free, R.drawable.unfree_};

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
        time1 = (TextView) findViewById(R.id.timeShow1);
        time2 = (TextView) findViewById(R.id.timeShow2);
        time3 = (TextView) findViewById(R.id.timeShow3);
        st1 = (TextView) findViewById(R.id.statusShow1);
        st2 = (TextView) findViewById(R.id.statusShow2);
        st3 = (TextView) findViewById(R.id.statusShow3);
        img1 = (ImageView) findViewById(R.id.imageShow1);
        img2 = (ImageView) findViewById(R.id.imageShow2);
        img3 = (ImageView) findViewById(R.id.imageShow3);
        shownTable = (LinearLayout) findViewById(R.id.LayoutHide);
        LayOne = (LinearLayout) findViewById(R.id.Lay1);
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
                bookMenu();

            }
        });
    }
    public void bookMenu(){
        shownTable.setVisibility(View.VISIBLE);
        LayOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(BookingActivity.this);
                builder.setMessage("รับขนมจีบซาลาเปาเพิ่มมั้ยครับ?");
                builder.setPositiveButton("รับ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                "ขอบคุณครับ", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("ไม่รับ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        img1.setImageResource(image[0]);
        img2.setImageResource(image[0]);
        img3.setImageResource(image[0]);
        time1.setText("18.00 - 20.00");
        time2.setText("20.00 - 22.00");
        time3.setText("22.00 - 00.00");
        st1.setText("Status : ว่าง");
        st2.setText("Status : ว่าง");
        st3.setText("Status : ว่าง");
    }

}
