package com.example.dell.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> title,desc,image,vote_average,vote_count,date;
    ArrayList<Integer> id;
    BroadcastReceiver receiver;
    GridView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = new ArrayList<String>();
        image = new ArrayList<String>();
        vote_average = new ArrayList<String>();
        vote_count = new ArrayList<String>();
        desc = new ArrayList<String>();
        id = new ArrayList<Integer>();
        date=new ArrayList<String>();
        list = (GridView) findViewById(R.id.view);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


         receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle b = intent.getExtras();
                String popular = b.getString("popularity");
                title.clear();
                image.clear();
                desc.clear();
                vote_count.clear();
                vote_average.clear();
                id.clear();
                int key = b.getInt("key");
                try {
                    JSONObject object = new JSONObject(popular);
                    JSONArray array = object.getJSONArray("results");
                    for(int i = 0;i<array.length();i++){
                        JSONObject result_object=array.getJSONObject(i);
                        title.add(result_object.getString("title"));
                        image.add("http://image.tmdb.org/t/p/w185"+result_object.getString("poster_path"));
                        desc.add(result_object.getString("overview"));
                        vote_average.add(result_object.getString("vote_average"));
                        vote_count.add(result_object.getString("vote_count"));
                        id.add(result_object.getInt("id"));
                        date.add(result_object.getString("release_date"));
                    }
                    myAdapter adapter = new myAdapter(MainActivity.this,title,desc,image,vote_average,vote_count,date,key,id);
                    list.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        int key1=0;
        if(getIntent().getExtras()!=null) {
            key1 = getIntent().getExtras().getInt("keyforward");
        }
        if(key1==1){
            registerReceiver(receiver,new IntentFilter("action"));
            Intent i=new Intent(MainActivity.this,MyIntentService.class);
            i.putExtra("src","https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=d04160312987af22a80ba27b59cd080c");
            startService(i);
        }else if (key1==2){
            registerReceiver(receiver,new IntentFilter("action"));
            Intent i=new Intent(MainActivity.this,MyIntentService.class);
            i.putExtra("src","https://api.themoviedb.org/3/discover/movie?sort_by=toprated.desc&api_key=d04160312987af22a80ba27b59cd080c");
            startService(i);
        }else if (key1==3){
            getdata();
        }else {
            registerReceiver(receiver, new IntentFilter("action"));
            Intent i = new Intent(MainActivity.this, MyIntentService.class);
            i.putExtra("src", "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=d04160312987af22a80ba27b59cd080c");
            startService(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int m_id=item.getItemId();
        if(m_id==R.id.popular){
            registerReceiver(receiver,new IntentFilter("action"));
            Intent i=new Intent(MainActivity.this,MyIntentService.class);
            i.putExtra("src","https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=d04160312987af22a80ba27b59cd080c");
            startService(i);
        }else if(m_id==R.id.toprated){
            registerReceiver(receiver,new IntentFilter("action"));
            Intent i=new Intent(MainActivity.this,MyIntentService.class);
            i.putExtra("src","https://api.themoviedb.org/3/discover/movie?sort_by=toprated.desc&api_key=d04160312987af22a80ba27b59cd080c");
            startService(i);
        }else if(m_id==R.id.favorite){
           getdata();
        }
        return super.onOptionsItemSelected(item);
    }
    public void getdata(){
        title.clear();
        image.clear();
        desc.clear();
        id.clear();
        vote_average.clear();
        date.clear();
        MyDataBase dataBase = new MyDataBase(this);
        SQLiteDatabase liteDatabase = dataBase.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("select * from "+MyDataBase.TABLE_NAME,null);

        if (cursor.moveToFirst()){
            do {
                id.add(cursor.getInt(cursor.getColumnIndex(MyDataBase.COLUMN_ID)));
                title.add(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_TITLE)));
                image.add(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_POSTER)));
                vote_average.add(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_VOTE)));
                date.add(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_RELEASEDATE)));
                desc.add(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_DESC)));
                vote_count.add(cursor.getString(cursor.getColumnIndex(MyDataBase.COLUMN_VOTEC)));
            }while (cursor.moveToNext());
        }
        myAdapter adapter = new myAdapter(MainActivity.this,title,desc,image,vote_average,vote_count,date,3,id);
        list.setAdapter(adapter);
    }

}
