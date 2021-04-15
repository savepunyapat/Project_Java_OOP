package com.example.SCMusic;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class testDeleteActivity extends AppCompatActivity {
    Button btnBack;
    Button btnBook;
    Button btnUnBook;
    TextView txtUid;
    TextView txtStatus;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bio_main);
        init();


    }
    private void init(){
        btnBack = (Button) findViewById(R.id.btnBackBio);
        btnBook = (Button) findViewById(R.id.btnBook);
        btnUnBook = (Button) findViewById(R.id.btnUnBook);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtUid = (TextView) findViewById(R.id.txtuid);
    }
    private void ShowData(){
        db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String uid = snapshot.child("Date").child("1 Mar").child("Room1").child("Uid").getValue(String.class);
                /*String post = snapshot.child(dateSelect.get(datePost) + " " + monthSelect.get(monthPost)).child("Room1").child("Status").getValue(String.class);
                String post2 = snapshot.child(dateSelect.get(datePost) + " " + monthSelect.get(monthPost)).child("Room2").child("Status").getValue(String.class);
                String post3 = snapshot.child(dateSelect.get(datePost) + " " + monthSelect.get(monthPost)).child("Room3").child("Status").getValue(String.class);
                String uid1 = snapshot.child(dateSelect.get(datePost) + " " + monthSelect.get(monthPost)).child("Room1").child("Uid").getValue(String.class);
                String uid2 = snapshot.child(dateSelect.get(datePost) + " " + monthSelect.get(monthPost)).child("Room2").child("Uid").getValue(String.class);
                String uid3 = snapshot.child(dateSelect.get(datePost) + " " + monthSelect.get(monthPost)).child("Room3").child("Uid").getValue(String.class);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
