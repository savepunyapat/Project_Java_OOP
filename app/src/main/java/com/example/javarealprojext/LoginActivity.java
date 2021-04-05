package com.example.javarealprojext;

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

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnBack;
    EditText editEmail;
    EditText editPwd;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                userLogin();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        btnBack = (Button) findViewById(R.id.btnBackLogin);
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
                    Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this,"Error!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
