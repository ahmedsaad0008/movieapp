package com.example.dell.project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 10/2/2017.
 */

public class AdapterVideos extends BaseAdapter {
    Activity activity;
    ArrayList<String> name,key;

    public AdapterVideos(Activity activity, ArrayList<String> name, ArrayList<String> key) {
        this.activity = activity;
        this.name = name;
        this.key = key;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.row_v,null);
        TextView names = (TextView)view.findViewById(R.id.namet);
        names.setText(name.get(position));
        ImageView img = (ImageView)view.findViewById(R.id.imgvid);
        String img_url= "http://img.youtube.com/vi/"+key.get(position)+"/0.jpg";
        Picasso.with(activity).load(img_url).resize(330,240).into(img);
        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.youtube.com/watch?v="+key.get(position));
                i.setData(uri);
                activity.startActivity(i);
            }
        });
        return view;
    }
}
