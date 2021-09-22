package com.dpkpranay.myproject;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.sql.Ref;
import java.util.ArrayList;

public class StudentNotice extends AppCompatActivity {
    Button btn;
    DatabaseReference mDatabase;
    ListView lv;
    ArrayList<String> arr=new ArrayList<>();
    ArrayList<String> keylist=new ArrayList<>();
    Button but;
    String key;
    String value;
    String val;
    EditText enter;
    Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notice);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("user");
        lv=(ListView)findViewById(R.id.user);



        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(arrayAdapter);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                value=dataSnapshot.getValue(String.class);
                arr.add(value);
                keylist.add(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                arr.remove(value);
                keylist.remove(dataSnapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



