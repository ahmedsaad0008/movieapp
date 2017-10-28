package com.example.dell.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment2 extends Fragment {
    RequestQueue queue;
    StringRequest request;
    ArrayList<String> authors,contents;
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment2,null);
        TextView voteav=(TextView)view.findViewById(R.id.voteaverage);
        TextView voteco=(TextView)view.findViewById(R.id.votecount);
        voteav.setText(getArguments().getString("vote_average"));
        voteco.setText(getArguments().getString("vote_count"));
        listView = (ListView)view.findViewById(R.id.listre);
        authors = new ArrayList<String>();
        contents = new ArrayList<String>();
        String src = "https://api.themoviedb.org/3/movie/"+String.valueOf(getArguments().getInt("id"))+"/reviews?api_key=c1bbbe3cf81393fb767146c1ac186fe4";
        queue = Volley.newRequestQueue(getActivity());
        request = new StringRequest(Request.Method.GET, src, new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                try {
                    JSONObject object1 = new JSONObject(response2);
                    JSONArray array1 = object1.getJSONArray("results");
                    for(int i = 0;i<array1.length();i++){
                        JSONObject result_object1=array1.getJSONObject(i);
                        authors.add(result_object1.getString("author"));
                        contents.add(result_object1.getString("content"));
                    }
                    AdapterReview adapterReview = new AdapterReview(getActivity(),authors,contents);
                    listView.setAdapter(adapterReview);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);

        return view;
    }
}
