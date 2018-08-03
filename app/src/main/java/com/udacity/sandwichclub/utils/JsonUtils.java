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


    public static JSONArray sandwichArrayData;
    public static JSONArray detailsArrayData;

    public static void jsonNameStream(String[] json, String [] details) throws JSONException {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sandwichArrayData = new JSONArray(json);
            detailsArrayData = new JSONArray(details);
        }

    }

    public static Sandwich parseSandwichJson(String dataCurrent) throws JSONException {
        Sandwich sandwichInstace = new Sandwich();
        JSONObject dataObject  = new JSONObject(dataCurrent);

        List<String> alternativeName = Arrays
                .asList(dataObject.
                        getString("name")
                .split(","));

        JSONArray alternativeNameArray = new JSONArray(String.valueOf(alternativeName));


        JSONObject obj = alternativeNameArray
                .getJSONObject(0);

        List<String> alsoKnownAs =Arrays.asList(obj
                .getString("alsoKnownAs")
                .replace("[","")
                .replace("]","")
                .replace("\"","")
                .split(","));


        List<String> ingredientsList = Arrays
                .asList(dataObject.getString("ingredients")
                .replace("[","")
                .replace("]","").replace("\"","")
                .split(","));

        sandwichInstace.setImage(dataObject.getString("image"));


        Sandwich sandwich = new Sandwich(obj.getString("mainName"),
                alsoKnownAs,
                dataObject.getString("placeOfOrigin"),
                dataObject.getString("description"),
                dataObject.getString("image"),ingredientsList
                );

        return sandwich;
    }


}
