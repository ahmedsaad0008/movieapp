package com.example.dell.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 9/23/2017.
 */

public class MyDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydatabase";
    public static final String TABLE_NAME = "movies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE= "title";
    public static final String COLUMN_POSTER= "poster";
    public static final String COLUMN_DESC= "overview";
    public static final String COLUMN_RELEASEDATE = "release_date";
    public static final String COLUMN_VOTE = "vote_average";
    public static final String COLUMN_VOTEC = "vote_count";
    static final int version1=1;
    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, version1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_Create = "CREATE TABLE "+TABLE_NAME+" ("+
                COLUMN_ID+" INTEGER PRIMARY KEY, "+
                COLUMN_TITLE+" TEXT NOT NULL, "+
                COLUMN_DESC+" TEXT NOT NULL, "+
                COLUMN_POSTER+" TEXT NOT NULL, "+
                COLUMN_RELEASEDATE+" TEXT NOT NULL, "+
                COLUMN_VOTE+" TEXT NOT NULL, "+
                COLUMN_VOTEC+" TEXT NOT NULL " + ");";

        db.execSQL(SQL_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
