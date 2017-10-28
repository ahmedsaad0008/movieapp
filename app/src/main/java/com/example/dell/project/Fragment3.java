package com.example.dell.project;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_fragment3,null);
        final Button add = (Button)view.findViewById(R.id.add);
        final Button delete = (Button)view.findViewById(R.id.delete);
        ImageView imageView = (ImageView)view.findViewById(R.id.image);
        final TextView overview=(TextView)view.findViewById(R.id.overview);
        TextView title=(TextView)view.findViewById(R.id.title);
        TextView date = (TextView)view.findViewById(R.id.date);
        Picasso.with(getContext()).load(getArguments().getString("image")).resize(500,500).into(imageView);
        overview.setText(getArguments().getString("overview"));
        title.setText(getArguments().getString("title"));
        date.setText(getArguments().getString("date"));
        MyDataBase dataBase = new MyDataBase(getContext());
        SQLiteDatabase liteDatabase = dataBase.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("select * from "+MyDataBase.TABLE_NAME+" where "+MyDataBase.COLUMN_ID+" =?",new String[]{String.valueOf(getArguments().getInt("id"))});
        if (cursor.moveToFirst()) {
            add.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
        }else{
            delete.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=getArguments().getInt("id");
                String title=getArguments().getString("title");
                String desc=getArguments().getString("overview");
                String image = getArguments().getString("image");
                String date = getArguments().getString("date");
                String vote_ave=getArguments().getString("vote_average");
                String vote_count=getArguments().getString("vote_count");
                MyDataBase mydb = new MyDataBase(getContext());
                SQLiteDatabase liteDatabase = mydb.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(MyDataBase.COLUMN_ID,id);
                values.put(MyDataBase.COLUMN_TITLE,title);
                values.put(MyDataBase.COLUMN_DESC,desc);
                values.put(MyDataBase.COLUMN_POSTER,image);
                values.put(MyDataBase.COLUMN_RELEASEDATE,date);
                values.put(MyDataBase.COLUMN_VOTE,vote_ave);
                values.put(MyDataBase.COLUMN_VOTEC,vote_count);
                liteDatabase.insert(MyDataBase.TABLE_NAME,null,values);
                add.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Movie Added to Favourites", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase dataBase1 = new MyDataBase(getContext());
                SQLiteDatabase liteDatabase1 = dataBase1.getReadableDatabase();
                liteDatabase1.delete(MyDataBase.TABLE_NAME,MyDataBase.COLUMN_ID+"=?",new String[]{String.valueOf(getArguments().getInt("id"))});
                delete.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Movie Deleted from Favourites", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
