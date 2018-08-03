package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            parseJSONdata();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        try {
            parseJSONdata();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSONdata() throws JSONException {

        final String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        final String[] sandwichDetails = getResources().getStringArray(R.array.sandwich_details);

//            final JSONObject sandwichDetails = new JSONObject(String.valueOf(getResources()
//                    .getStringArray(R.array.sandwich_details)));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonUtils.jsonNameStream(sandwiches, sandwichDetails);
//                   JSONArray details = JsonUtils.jsonNameStream(sandwichDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        loadUI();
    }

    private void loadUI() {

        Log.d("Sandwich Details",String.valueOf(String.valueOf(JsonUtils.sandwichArrayData)));
        Log.d("Sandwich Details Array",String.valueOf(String.valueOf(JsonUtils.detailsArrayData)));

        RecyclerView mRecyclerView = findViewById(R.id.rv_sandwiches);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutmanager);

         RecyclerView.Adapter mAdapter = new MainActivityAdapter(this,
                 JsonUtils.sandwichArrayData, JsonUtils.detailsArrayData);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
