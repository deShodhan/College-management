package com.dpkpranay.myproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main4Activity extends AppCompatActivity {
    Button act;
    Button act2;
    Button act3;
    Button btnN;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        act=(Button)findViewById(R.id.Act);
        act2=(Button)findViewById(R.id.Act2);
        act3=(Button)findViewById(R.id.Act3);
        Button but=(Button)findViewById(R.id.button5);
        btnN=(Button)findViewById(R.id.button3);
        btn=(Button)findViewById(R.id.button7);

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6=new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent6);
            }
        });
        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent7=new Intent(Main4Activity.this,addStatusActivity.class);
               startActivity(intent7);
            }
        });
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main4Activity.this,image_listActivity.class));
            }
        });

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FirebaseAuth.getInstance().signOut();
               startActivity(new Intent(Main4Activity.this,LoginActivity.class));
           }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,Main2Activity.class));
            }
        });
    }
}
