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


public class VideoService extends IntentService {


    public VideoService() {
        super("VideoService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String id_m = intent.getExtras().getString("id");
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/"+id_m+"/videos?api_key=c1bbbe3cf81393fb767146c1ac186fe4");
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream input = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = "",vresponse="";
            while((line = reader.readLine())!=null){
                vresponse+=line;
            }
            Intent i = new Intent("videos");
            i.putExtra("video",vresponse);
            sendBroadcast(i);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
