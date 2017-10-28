package com.example.dell.project;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Main2Activity extends AppCompatActivity {


    String title,overview;
    TabLayout tab;
    ViewPager pager;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tab = (TabLayout)findViewById(R.id.tab_layout);
        pager = (ViewPager)findViewById(R.id.pager);
        bundle=getIntent().getExtras();
        title=bundle.getString("title");
        overview=bundle.getString("overview");
        final String image = bundle.getString("poster");
        final String vote_count=bundle.getString("vote_count");
        final String vote_average=bundle.getString("vote_average");
        final String date=bundle.getString("date");
        final int id = bundle.getInt("id");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton back = (ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),title,overview,image,vote_count,vote_average,date,id);
        pager.setAdapter(adapter);

        tab.addTab(tab.newTab().setText("Details"));
        tab.addTab(tab.newTab().setText("Average"));
        tab.addTab(tab.newTab().setText("Trailers"));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"title:\n"+title+"\n\n"+"OverView:\n"+overview);
            intent.setType("text/plain");
            startActivity(intent);
        }else if(id==R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(Main2Activity.this,MainActivity.class);
        in.putExtra("keyforward",getIntent().getExtras().getInt("key"));
        startActivity(in);
    }
}
