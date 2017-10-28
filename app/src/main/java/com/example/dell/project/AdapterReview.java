package com.example.dell.project;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 9/28/2017.
 */

public class AdapterReview extends BaseAdapter {
    Activity activity;
    ArrayList<String> author,m_content;

    public AdapterReview(Activity activity, ArrayList<String> author, ArrayList<String> m_content) {
        this.activity = activity;
        this.author = author;
        this.m_content = m_content;
    }

    @Override
    public int getCount() {
        return author.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.row_review,null);
        TextView aut = (TextView)view.findViewById(R.id.author);
        TextView cont = (TextView)view.findViewById(R.id.content);
        aut.setText(author.get(position));
        cont.setText(m_content.get(position));
        return view;
    }
}
