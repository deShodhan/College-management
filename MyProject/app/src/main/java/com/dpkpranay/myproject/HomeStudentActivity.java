package com.dpkpranay.myproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class HomeStudentActivity extends AppCompatActivity {
    Button butto;
    Button sn;
    ImageView img;
    AdView mAd;
    InterstitialAd interstitialAd;
    Button but6;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);
        butto=(Button)findViewById(R.id.button4);
        sn=(Button)findViewById(R.id.std_notice);
        img=(ImageView) findViewById(R.id.imageView3);
        but6=(Button)findViewById(R.id.buttonew);

        mAd=(AdView)findViewById(R.id.adView);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        AdRequest adRequest=new AdRequest.Builder().addTestDevice("191F86AA961CFE847AC29AD23241DFFD").build();
        mAd.loadAd(adRequest);

        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        interstitialAd.setAdListener(new AdListener()
                                     {
                                         @Override
                                         public void onAdClosed() {
                                             startActivity(new Intent(HomeStudentActivity.this, StudentNotice.class));
                                             interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("191F86AA961CFE847AC29AD23241DFFD").build());
                                         }
                                     }

        );


        butto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeStudentActivity.this,image_listActivity.class));
            }
        });

        sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                else {
                    startActivity(new Intent(HomeStudentActivity.this, StudentNotice.class));
                }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeStudentActivity.this,About.class));
            }
        });

        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(HomeStudentActivity.this,Main5Activity.class));
            }
        });
    }

}
