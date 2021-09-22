package com.dpkpranay.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startingActivity extends AppCompatActivity {
    Button buttonStudent;
    Button buttonTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        buttonStudent=(Button)findViewById(R.id.button_student);
        buttonTeacher=(Button)findViewById(R.id.button_teacher);

        buttonTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intn=new Intent(startingActivity.this,LoginActivity.class);
                startActivity(intn);
            }
        });

        buttonStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intn3=new Intent(startingActivity.this,HomeStudentActivity.class);
             startActivity(intn3);
            }
        });
    }
}
