package com.kartnap.chandan.newswallet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Share extends AppCompatActivity {
    ImageView share,back,copy;
    ClipboardManager clipboardManager;
    ClipData clipData;
    TextView refCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        share = (ImageView)findViewById(R.id.share);
        back = (ImageView)findViewById(R.id.backArrow);
        copy = (ImageView)findViewById(R.id.copy_to_clip);
        refCode=(TextView)findViewById(R.id.coupon_code);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Share.this,Home.class));
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                clipData = ClipData.newPlainText("Refer Code" ,refCode.getText().toString() );
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getApplicationContext(),"Copied : "+clipData.getItemAt(0).toString(),Toast.LENGTH_LONG).show();

            }
        });




        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,  refCode.getText().toString() );
                startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });

    }

//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id){
//            case R.id.share :
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("plain/text");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, "hello.");
//                startActivity(Intent.createChooser(shareIntent, "Share using"));
//                break;
//            case R.id.backArrow :
//                startActivity(new Intent(Share.this,Home.class));
//                break;
//            case R.id.copy_to_clip :
//                clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
//                clipData = ClipData.newPlainText("Refer Code" ,refCode.getText().toString() );
//                clipboardManager.setPrimaryClip(clipData);
//
//                Toast.makeText(getApplicationContext(),"Copied : "+clipData.toString(),Toast.LENGTH_LONG).show();
//                break;
//
//        }
//    }
}
