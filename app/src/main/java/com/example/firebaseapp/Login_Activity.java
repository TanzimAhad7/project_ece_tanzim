package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    EditText userETLogin,passETLogin;
    Button LoginBtn,RegisterBtn;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userETLogin =  findViewById(R.id.editTextLogin);
        passETLogin = findViewById(R.id.editTextPassword);
        LoginBtn = findViewById(R.id.buttonLogin);
        RegisterBtn = findViewById(R.id.button2);

        auth = FirebaseAuth.getInstance();

        //register button

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        // login button

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_text = userETLogin.getText().toString();
                String pass_text = passETLogin.getText().toString();

                // empty check
                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(Login_Activity.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(email_text,pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(Login_Activity.this, MainActivity.class);
                                      //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();

                                    }
                                    else{
                                        Toast.makeText(Login_Activity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
    }
}