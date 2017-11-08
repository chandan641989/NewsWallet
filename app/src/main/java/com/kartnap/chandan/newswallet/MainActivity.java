package com.kartnap.chandan.newswallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.sbstrm.appirater.Appirater;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class MainActivity extends AppCompatActivity {
    // Step 1: designate a style
    private EditText number;
    private Button submit;
    Context context = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number = (EditText)findViewById(R.id.input_number);
        submit = (Button)findViewById(R.id.btn_verify);
        AppRater.app_launched(this);


        //number.setSelection(number.length()/2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long num = Long.parseLong(number.getText().toString());
                int x= Integer.parseInt(Long.toString(num).substring(0, 1));
                if(x >=7 && x<=9){
                    if (x==0){
                        Toast.makeText(getApplicationContext(),"Mobile Number Should Start with 7, 8 or 9 ",Toast.LENGTH_LONG).show();
                        return;

                    }
                        Toast.makeText(getApplicationContext()," 1 Toast Submit Button Pressed",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),LoginOTP.class));
                        Toast.makeText(getApplicationContext(),"Submit Button Pressed",Toast.LENGTH_LONG).show();
                        //finish();

                }else {
                    Toast.makeText(getApplicationContext(),"Mobile Number Should Start with 7, 8 or 9",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
