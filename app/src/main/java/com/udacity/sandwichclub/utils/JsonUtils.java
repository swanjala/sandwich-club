package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static Sandwich sandwich = new Sandwich();
    private static JSONArray sandwichData;

    public static void jsonDataStream(String[] json) throws JSONException {
        JSONArray sandwichArray = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sandwichData = new JSONArray(json);
        }

    }

    public static Sandwich parseSandwichJson(ArrayList dataCurrent) throws JSONException {

           sandwich.setDescription();
           sandwich.setPlaceOfOrigin();
           sandwich.setImage();
           sandwich.setIngredients();
           sandwich.setAlsoKnownAs();
           sandwich.setMainName();


        return sandwich;
    }


}
