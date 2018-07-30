package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {


    public static JSONArray sandwichData;

    public static void jsonDataStream(String[] json) throws JSONException {
        JSONArray sandwichArray = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sandwichData = new JSONArray(json);
        }

    }

    public static Sandwich parseSandwichJson(JSONObject dataCurrent) throws JSONException {
//        JSONObject data;
//        data= dataCurrent.getJSONObject(); --> remember to add this in your recycler view

        List<String> alternativeName = Arrays.asList(dataCurrent.
                        getString("alsoKnownAs").split(","));

        List<String> ingredientsList = Arrays.asList(dataCurrent.
                getString("ingredients").split(","));

        Sandwich sandwich = new Sandwich(dataCurrent.getString("mainName"),
                alternativeName,
                dataCurrent.getString("placeOfOrigin"),
                dataCurrent.getString("description"),
                dataCurrent.getString("image"),ingredientsList
                );


        return sandwich;
    }


}
