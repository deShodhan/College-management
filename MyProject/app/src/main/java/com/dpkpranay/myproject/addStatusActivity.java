package com.dpkpranay.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addStatusActivity extends AppCompatActivity {
    Button buttonPost;
    EditText editTextPost;
    String dpk;
    DatabaseReference mDatabase;
    String val;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_status);
        editTextPost=(EditText)findViewById(R.id.editText_post);
        buttonPost=(Button)findViewById(R.id.button_post);
        post=(Button)findViewById(R.id.post);
        mDatabase=FirebaseDatabase.getInstance().getReference().child("user");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpk=editTextPost.getText().toString();
                val=mDatabase.push().getKey();
                mDatabase.push().setValue(dpk);
                Toast.makeText(getApplicationContext(), "posted!", Toast.LENGTH_LONG).show();
                editTextPost.setText("");
            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(addStatusActivity.this,Main2Activity.class));
            }
        });
    }
}
