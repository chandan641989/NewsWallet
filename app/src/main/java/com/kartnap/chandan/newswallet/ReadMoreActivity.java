package com.kartnap.chandan.newswallet;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.common.hash.HashingOutputStream;

public class ReadMoreActivity extends AppCompatActivity {
    String overView;
    ImageView thumbnail_image;
    TextView readMore_link;
    TextView overView_description,newsTitle;
    String url;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);
        overView =getIntent().getExtras().getString("overview");
        back =(ImageView)findViewById(R.id.backArrow);
        overView_description = (TextView)findViewById(R.id.news_overview);
        thumbnail_image = (ImageView)findViewById(R.id.thumbnail);
        readMore_link = (TextView)findViewById(R.id.readMoreLink);
        newsTitle = (TextView)findViewById(R.id.news_title); ;
        overView_description.setText(overView);
        String thumnailImage=getIntent().getExtras().getString("linkToImage");
        Glide.with(getApplicationContext()).load(Uri.parse(thumnailImage))
                .placeholder(R.drawable.logo)
                .into(thumbnail_image);
        //Toast.makeText(getApplicationContext(),""+thumnailImage,Toast.LENGTH_LONG).show();
        newsTitle.setText(getIntent().getExtras().getString("title"));
        url = getIntent().getExtras().getString("link");
        readMore_link.setText("read more...");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReadMoreActivity.this, Home.class));
            }
        });

    }
    public void open_url(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
