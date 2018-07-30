package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseJSONdata();

    }

    @Override
    protected void onResume(){
        super.onResume();
        parseJSONdata();
    }

    private void parseJSONdata(){

        final String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonUtils.jsonDataStream(sandwiches);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        loadUI(JsonUtils.sandwichData);
    }

    private void loadUI(JSONArray sandwichLoadedData){

//    Data goes to RecyclerView
        Log.d("DataSampel", String.valueOf(sandwichLoadedData));
    }


    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
