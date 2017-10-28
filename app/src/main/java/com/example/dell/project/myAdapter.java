package com.example.dell.project;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 9/22/2017.
 */

public class myAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<String> titles,overviews,images,vote_average,vote_count,date;
    int state;
    ArrayList<Integer> id;

    public myAdapter(Activity activity, ArrayList<String> titles, ArrayList<String> overviews, ArrayList<String> images, ArrayList<String> vote_average, ArrayList<String> vote_count, ArrayList<String> date, int state, ArrayList<Integer> id) {
        this.activity = activity;
        this.titles = titles;
        this.overviews = overviews;
        this.images = images;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.date = date;
        this.state = state;
        this.id = id;
    }

    @Override
    public int getCount() {
        return images.size();
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
        View view1 = activity.getLayoutInflater().inflate(R.layout.row,null);
        ImageButton image =(ImageButton) view1.findViewById(R.id.image);
        Picasso.with(activity).load(images.get(position)).resize(535,500).error(R.drawable.iconsunavailable0).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity,Main2Activity.class);
                i.putExtra("title",titles.get(position));
                i.putExtra("overview",overviews.get(position));
                i.putExtra("id",id.get(position));
                i.putExtra("poster",images.get(position));
                i.putExtra("vote_count",vote_count.get(position));
                i.putExtra("vote_average",vote_average.get(position));
                i.putExtra("date",date.get(position));
                i.putExtra("key",state);
                activity.startActivity(i);
                activity.finish();
            }
        });
        return view1;
    }
}
