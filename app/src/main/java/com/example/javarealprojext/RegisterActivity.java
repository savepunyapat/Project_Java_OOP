package com.example.javarealprojext;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    CardView register;
    CardView backer;
    EditText editEmail;
    EditText editPass1;
    EditText editPass2;
    FirebaseAuth regAuth;
    DatabaseReference dr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        init();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regAuth = FirebaseAuth.getInstance();
                dr = FirebaseDatabase.getInstance().getReference();
                registerUser();
            }
        });
        backer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void init(){
        register = (CardView) findViewById(R.id.registerpageBtn);
        backer = (CardView) findViewById (R.id.backRegisBtn);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPass1 = (EditText) findViewById(R.id.editPass1);
        editPass2 = (EditText) findViewById(R.id.editPass2);
    }
    private void registerUser(){
        String email = editEmail.getText().toString().trim();
        String pass1 = editPass1.getText().toString().trim();
        String pass2 = editPass2.getText().toString().trim();

        if(email.isEmpty()){
            editEmail.setError("Input Email!!!");
            editEmail.requestFocus();
            return;
        }
        if (pass1.length() < 6){
            editPass1.setError("Your Password too short!");
            editPass1.requestFocus();
            return;
        }
        if(pass1.isEmpty()){
            editPass1.setError("Input Password");
            editPass1.requestFocus();
            return;
        }
        if (pass2.isEmpty()){
            editPass2.setError("Input same Password");
            editPass2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Invalid Email Try again.");
            editEmail.requestFocus();
            return;
        }

        regAuth.createUserWithEmailAndPassword(email,pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String stremail = editEmail.getText().toString().trim();
                            String strpss = editPass1.getText().toString().trim();
                            UserCreate u1 = new UserCreate(stremail,strpss);
                            dr.child("Admins").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(strpss);
                            Toast.makeText(RegisterActivity.this,"Success",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(RegisterActivity.this,"Unsuccess",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
    private void addToast(String sms){
        Toast.makeText(this,sms,Toast.LENGTH_LONG).show();
    }
}
