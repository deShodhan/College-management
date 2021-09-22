package com.dpkpranay.myproject;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    EditText et;
    static final int PICK_IMAGE = 1;
    Button mButton_choose;
    TextView mTextView_show_upolads;
    EditText mEditTextFileName;

    StorageReference mStrorageRef;
    DatabaseReference mDatabaseRef;

    ProgressDialog pg;

    Button capture;
    Button go;
    Button home;
    ImageView show_image;

    TextView textview2;

    Uri muri;

    Uri downloadUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pg = new ProgressDialog(this);

        mButton_choose = (Button) findViewById(R.id.button_choose);
        mEditTextFileName = (EditText) findViewById(R.id.editText2);

        mStrorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("status");

        capture = (Button) findViewById(R.id.button_capture);
        show_image = (ImageView) findViewById(R.id.image_show);


        go = (Button) findViewById(R.id.go);
        home = (Button) findViewById(R.id.Home);


        mButton_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_PICK);
                intent3.setType("image/*");
                startActivityForResult(intent3, PICK_IMAGE);

            }
        });
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent();
                intent4.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent4, PICK_IMAGE);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (muri!=null) {
                    pg.setMessage("Uploading..");
                    pg.show();
                    StorageReference ref=mStrorageRef.child("photos"+System.currentTimeMillis()+"."+getImageExt(muri));
                    ref.putFile(muri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pg.dismiss();
                            Toast.makeText(getApplicationContext(),"uploaded",Toast.LENGTH_SHORT).show();
                            ImageUpload imageUpload=new ImageUpload(mEditTextFileName.getText().toString(),taskSnapshot.getDownloadUrl().toString());
                            String uploadId=mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(imageUpload);

                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"please select an image",Toast.LENGTH_SHORT).show();
                }
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent9 = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent9);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            // pg.setMessage("Uploading..");
            // pg.show();
            muri = data.getData();

            // StorageReference filePath = mStrorageRef.child("photos").child(muri.getLastPathSegment());
            // filePath.putFile(muri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            //     @Override
            //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            //        pg.dismiss();
            //        Toast.makeText(getApplicationContext(), "Upload successfull", Toast.LENGTH_LONG).show();
            //        downloadUri = taskSnapshot.getDownloadUrl();
            //        Picasso.with(MainActivity.this).load(downloadUri).into(show_image);
            //    }
            //});

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), muri);
                show_image.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    public String getImageExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(muri));

    }
    public void btnShow(View vm){
        Intent intent11=new Intent(MainActivity.this,image_listActivity.class);
        startActivity(intent11);

    }
}