package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();
        List<String> alsoKnownAs = new ArrayList<>();

        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject sandwichDetails = sandwich.getJSONObject("name");

             mainName = sandwichDetails.optString("mainName");
             placeOfOrigin = sandwich.optString("placeOfOrigin");
             description = sandwich.optString("description");
             image = sandwich.optString("image");

             ingredients = toList(sandwich.getJSONArray("ingredients"));
             alsoKnownAs = toList(sandwichDetails.getJSONArray("alsoKnownAs"));


        } catch (final JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients) ;
    }



    private static List<String> toList (JSONArray toList){
        List<String> list = new ArrayList<>(0);
        if (toList!=null){
            for (int i=0; i<toList.length();i++){
                try {
                    list.add(toList.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
