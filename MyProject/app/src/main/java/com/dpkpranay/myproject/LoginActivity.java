package com.dpkpranay.myproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {
    Button mLoginbutton;
    EditText mEmailField;
    EditText mPasswardField;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailField=(EditText)findViewById(R.id.editText);
        mPasswardField=(EditText)findViewById(R.id.editText4);
        mLoginbutton=(Button)findViewById(R.id.button2);

        mAuth=FirebaseAuth.getInstance();
        authListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(LoginActivity.this,Main4Activity.class));
                }
            }
        };

        mLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authListener);
    }

    private void startSignin(){
        String email=mEmailField.getText().toString();
        String passward=mPasswardField.getText().toString();

        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(passward)) {

            Toast.makeText(LoginActivity.this,"fields are empty",Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, passward).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Incorrect credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
