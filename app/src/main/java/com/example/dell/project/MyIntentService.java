package com.example.dell.project;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String src = intent.getExtras().getString("src");
        try {
            URL url = new URL(src);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream input = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = "",response="";
            while((line = reader.readLine())!=null){
                response+=line;
            }
            Intent i = new Intent("action");
            if(src.equals("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=d04160312987af22a80ba27b59cd080c")){
                i.putExtra("key",1);
            }else if(src.equals("https://api.themoviedb.org/3/discover/movie?sort_by=toprated.desc&api_key=d04160312987af22a80ba27b59cd080c")){
                i.putExtra("key",2);
            }
            i.putExtra("popularity",response);
            sendBroadcast(i);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
