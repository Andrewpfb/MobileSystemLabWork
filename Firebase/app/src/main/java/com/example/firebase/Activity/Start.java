package com.example.firebase.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Start extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText ETemail, ETpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                   // Intent intent = new Intent(Start.this,MainActivity.class);
                    //startActivity(intent);
                } else {
                    // User is signed out
                }
            }
        };

        ETemail = (EditText) findViewById(R.id.et_email);
        ETpassword = (EditText) findViewById(R.id.et_password);

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null) {
            /*Intent intent = new Intent(Start.this,MainActivity.class);
            startActivity(intent);*/
        }
    }

    public void OnClick(View v) {
        if (v.getId() == R.id.btn_sign_in) {
            if(ETemail.getText().length()!=0&ETpassword.getText().length()!=0) {
                Signing(ETemail.getText().toString(), ETpassword.getText().toString());
            }
            else{
                Toast.makeText(this, "Check field,please.", Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.btn_registration) {
            if(ETemail.getText().length()!=0&ETpassword.getText().length()!=0) {
                Registration(ETemail.getText().toString(), ETpassword.getText().toString());
            }
            else{
                Toast.makeText(this, "Check field,please.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Signing(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Start.this, "Auth is success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Start.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Start.this, "Auth is failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Registration(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Start.this, "Register is success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Start.this,MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(Start.this, "Register is failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
