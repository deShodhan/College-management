package com.dpkpranay.myproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class image_listActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        imgList=new ArrayList<>();
        lv=(ListView) findViewById(R.id.listViewImage);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("status");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ImageUpload img=snapshot.getValue(ImageUpload.class);
                    imgList.add(img);
                }

                adapter=new ImageListAdapter(image_listActivity.this,R.layout.image_item,imgList);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
              progressDialog.dismiss();
            }
        });
    }
}
