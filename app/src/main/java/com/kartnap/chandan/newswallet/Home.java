package com.kartnap.chandan.newswallet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kartnap.chandan.newswallet.api.Client;
import com.kartnap.chandan.newswallet.api.Service;
import com.kartnap.chandan.newswallet.data.News;
import com.kartnap.chandan.newswallet.data.NewsResponse;
import com.sbstrm.appirater.Appirater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> NewsList;
    private ProgressBar progressBar;
    //Animation shake;
   // TextView newsText;
    private RecyclerView.LayoutManager mLayoutManager;
    //private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    Appirater.RatingButtonListener ratingButtonListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        Appirater.appLaunched(getApplicationContext(),ratingButtonListener);

//        shake = AnimationUtils.loadAnimation(Home.this, R.anim.shake);
//        newsText.startAnimation(shake);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //sending user to wallet balance activity.
        if ( id== R.id.wallet_balance ){
            startActivity(new Intent(Home.this,WalletBalance.class));
        }
        //sending user to profile activity
        if(id == R.id.profile){
            startActivity(new Intent(Home.this,Profile.class));
        }
        if (id == R.id.nav_share){
            startActivity(new Intent(Home.this,Share.class));

        }
        if (id == R.id.nav_rate_us){
            //AppRater.app_launched(Home.this);
            AlertDialog.Builder alert = new AlertDialog.Builder(Home.this);
            alert.setTitle("Rate Us:");
            alert.setMessage("If you like this application,Please rate us." +
                    "Thanks for using our application.");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id="
                                        + getPackageName())));
                    }

                    dialog.dismiss();
                    finish();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            alert.create();
            alert.show();
//            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
//            startActivity(rateIntent);



        }

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.news_list);
        //progressBar = (ProgressBar)findViewById(R.id.progress);
        NewsList = new ArrayList<>();
        adapter = new NewsAdapter(this,NewsList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //loadJSON();
        String url ="http://198.37.118.35/news.php";
        new DownloadTask().execute(url);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        // gridLayoutManager=new GridLayoutManager(v.getContext(),2);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private class DownloadTask  extends AsyncTask<String,String,Integer> {
        @Override
        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Integer integer){
            //progressBar.setVisibility(View.GONE);

            if (integer == 1) {
                adapter = new NewsAdapter(getApplicationContext(), NewsList);

                recyclerView.setAdapter(adapter);


            } else {
                Toast.makeText(getApplicationContext(), "Data Fetching failed", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected Integer doInBackground(String...params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }

                    parseResult(response.toString());
                    result = 1;
                } else {
                    result = 0;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }


    }
    private void parseResult(String s) throws JSONException {
        //JSONArray response = new JSONArray(s);
        JSONObject response = new JSONObject(s);
        JSONArray result = response.optJSONArray("articles");
        NewsList = new ArrayList<>();
        for (int i = 0; i < result.length(); i++) {
            JSONObject product = result.optJSONObject(i);
            News item = new News();
            item.setTitle(product.optString("title"));
            item.setDescription(product.optString("description"));
            item.setUrl(product.optString("url"));
            item.setUrltoImage(product.optString("urlToImage"));
            NewsList.add(item);

        }


    }



    void showDialog() {
        FragmentDialog newFragment = FragmentDialog.newInstance(
                R.string.news_overview);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
    }



}
