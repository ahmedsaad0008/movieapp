package com.example.dell.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by DELL on 9/23/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    String title,overview,image ,vote_count,vote_average,date;
    int id ;

    public PagerAdapter(FragmentManager fm, String title, String overview, String image, String vote_count, String vote_average, String date, int id) {
        super(fm);
        this.title = title;
        this.overview = overview;
        this.image = image;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.date = date;
        this.id = id;
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("overview",overview);
        bundle.putString("image",image);
        bundle.putString("vote_count",vote_count);
        bundle.putString("vote_average",vote_average);
        bundle.putString("date",date);
        bundle.putInt("id",id);
        if(position == 0){
            Fragment3 fragment3=new Fragment3();
            fragment3.setArguments(bundle);
            return fragment3;
        } else  if(position == 1){
            Fragment2 fragment2 = new Fragment2();
            fragment2.setArguments(bundle);
            return fragment2;

        }else if(position==2){
            Fragment1 fragment1 = new Fragment1();
            fragment1.setArguments(bundle);
            return fragment1;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
