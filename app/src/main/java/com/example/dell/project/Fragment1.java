package com.example.dell.project;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    ArrayList<String>videos,names;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1,null);
        listView = (ListView) view.findViewById(R.id.listv);
        videos = new ArrayList<String>();
        names = new ArrayList<String>();
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle b = intent.getExtras();
                String video = b.getString("video");
                try {
                    JSONObject object = new JSONObject(video);
                    JSONArray array = object.getJSONArray("results");
                    for(int i = 0;i<array.length();i++){
                        JSONObject result_object=array.getJSONObject(i);
                        videos.add(result_object.getString("key"));
                        names.add(result_object.getString("name"));
                    }
                    AdapterVideos adapterVideos = new AdapterVideos(getActivity(),names,videos);
                    listView.setAdapter(adapterVideos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Intent i = new Intent(getActivity(),VideoService.class);
        i.putExtra("id",String.valueOf(getArguments().getInt("id")));
        getActivity().startService(i);
        getActivity().registerReceiver(receiver,new IntentFilter("videos"));

        return view;
    }
}
