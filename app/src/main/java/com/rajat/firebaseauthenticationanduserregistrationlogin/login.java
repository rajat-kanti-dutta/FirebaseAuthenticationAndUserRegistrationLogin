package com.rajat.firebaseauthenticationanduserregistrationlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextInputEditText emailWidget;
    TextInputEditText passwordWidget;
    Button submitBtn;
    ProgressBar pg;
    String email="";
    String password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        emailWidget = (TextInputEditText)findViewById(R.id.loginEmail);
        passwordWidget = (TextInputEditText) findViewById (R.id.loginPwd);
        submitBtn = (Button) findViewById(R.id.loginBtnSubmit);
        pg= (ProgressBar) findViewById(R.id.loginprogressBar);

        emailWidget.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = s.toString();
            }
        });
        //
        passwordWidget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);

                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                      if(task.isSuccessful()){
                                          //
                                      }
                                      else{
                                          //
                                      }
                            }
                        });
            }
        });

        //
    }
}