package com.rajat.firebaseauthenticationanduserregistrationlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    TextInputEditText emailWidget;
    TextInputEditText passwordWidget;
    Button submitBtn;
    ProgressBar pg;
    String email="";
    String password="";
    TextView alreadySignUP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding=ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        alreadySignUP = (TextView)findViewById(R.id.alreadySignUp);
        emailWidget = (TextInputEditText)findViewById(R.id.email);
        passwordWidget = (TextInputEditText) findViewById (R.id.pwd);
        submitBtn = (Button) findViewById(R.id.btnSubmit);
        pg= (ProgressBar) findViewById(R.id.progressBar);

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
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Sign in Success
                                    pg.setVisibility(View.GONE);
                                    Log.d("FIREBASEAUTH","Creation Of User : Success");
                                    emailWidget.setText("");
                                    passwordWidget.setText("");
                                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                   // FirebaseUser user = mAuth.getCurrentUser();
                                   // updateUI(user);
                                }
                                else{
                                    pg.setVisibility(View.GONE);
                                       //if sign in fails
                                       Log.w("FIREBASEAUTH","Creation of User: Failed!!!!");
                                    Toast.makeText(getApplicationContext(),"Authentication Failed!!!!!",Toast.LENGTH_LONG).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }
        });
        //
        alreadySignUP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //send to login activity
                startActivity(new Intent(MainActivity.this,login.class));
            }
        });
    }
}