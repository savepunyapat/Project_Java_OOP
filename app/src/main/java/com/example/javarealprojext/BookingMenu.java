package com.example.javarealprojext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BookingMenu extends AppCompatActivity {
    private ListView rlistView;
    String listTime[] = {"18.00 - 20.00","20.00 - 22.00","22.00 - 00.00"};
    String roomStatus[] = {"Available","Unavailable"};
    int image[] = {R.drawable.free,R.drawable.unfree_};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_menu);
        rlistView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this,listTime,roomStatus,image);
        rlistView.setAdapter(adapter);
    }
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String[] rlistTime;
        String[] rroomStatus;
        int[] rImage;

        MyAdapter (Context c, String[] time, String[] status, int[] img){
            super(c, R.layout.booking_row,R.id.statusShow,time);
            this.context = c;
            this.rlistTime = time;
            this.rroomStatus = status;
            this.rImage = img;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.booking_row, parent,false);
            ImageView images = row.findViewById(R.id.imageShow);
            TextView myTime = row.findViewById(R.id.timeShow);
            TextView myStatus = row.findViewById(R.id.statusShow);

            images.setImageResource(rImage[0]);
            myTime.setText(rlistTime[position]);
            myStatus.setText(roomStatus[0]);

            return row;
        }
    }

}
