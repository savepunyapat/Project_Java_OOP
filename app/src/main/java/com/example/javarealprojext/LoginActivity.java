package com.example.javarealprojext;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnReg;
    EditText editEmail;
    EditText editPwd;
    FirebaseAuth mAuth;
    DatabaseReference db;
    List<String> listTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                db = FirebaseDatabase.getInstance().getReference();
                userLogin();

                Intent intent = new Intent(LoginActivity.this,BookingActivity.class);
                startActivity(intent);
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        btnReg = (Button) findViewById(R.id.btnGoReg);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editEmail = (EditText) findViewById(R.id.editEmailLogin);
        editPwd = (EditText) findViewById(R.id.editPasswordLogin);
    }
    private void userLogin(){
        String email = editEmail.getText().toString().trim();
        String pwd = editPwd.getText().toString().trim();

        if(email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Invalid Email!");
            editEmail.requestFocus();
            return;
        }
        if(pwd.isEmpty()){
            editPwd.setError("Password is required!");
            editPwd.requestFocus();
            return;
        }
        if(pwd.length() < 6){
            editPwd.setError("Password must more than 6 char!");
            editPwd.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Date currentTime = Calendar.getInstance().getTime();
                    listTime = new ArrayList<String>();
                    String formattedDate = DateFormat.getDateInstance().format(currentTime);
                    listTime.add(currentTime.toString());
                    db.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(listTime);
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this,"Error!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}