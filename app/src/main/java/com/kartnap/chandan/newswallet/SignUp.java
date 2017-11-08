package com.kartnap.chandan.newswallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chandan on 10/31/2017.
 */

public class SignUp extends AppCompatActivity {
    Button button;
    EditText name,email,referCode;
    TelephonyManager telephonyManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        button = (Button)findViewById(R.id.btn_submit);
        name = (EditText)findViewById(R.id.input_name) ;
        referCode =(EditText)findViewById(R.id.referer_code) ;
        email  =(EditText)findViewById(R.id.input_email);
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String imei=telephonyManager.getDeviceId();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUp.this,Home.class));

            }
        });


    }
}
