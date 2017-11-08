package com.kartnap.chandan.newswallet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;
    //TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/IndieFlower.ttf");
        //splashText.setTypeface(tf);
        boolean check;
        check = checkInternetConnection();
        if (check == false){
            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));

        }

    }
    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    Intent intent = new Intent();
                    intent.setClass(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }, SPLASH_DISPLAY_LENGHT);

            return true;


                 // and close this Splash-Screen after some seconds.

        } else {
            Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_LONG).show();
            return false;


        }
    }
}
