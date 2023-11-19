package com.example.momkitchen.Manager;

import android.content.Context;


import com.example.momkitchen.Model.InfoModel;
import com.example.momkitchen.Utilities.JsonLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoManager {

    public static List<InfoModel> getAllSections(Context context) {
        List<InfoModel> allSections = new ArrayList<>();

        try {
            String file = "info.json";  // Assuming the JSON file is named "info.json"
            JSONArray jsonArray = JsonLoader.loadJson(context, file);
            int max = jsonArray.length();

            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                InfoModel section = createAppInfoSection(jsonObject);
                allSections.add(section);
            }
        } catch (Exception e) {
            // Handle exception or log error
            e.printStackTrace();
        }

        return allSections;
    }

    private static InfoModel createAppInfoSection(JSONObject jsonObject) {
        try {
            String title = jsonObject.getString("title");
            String desc = jsonObject.getString("desc");

            return new InfoModel(title, desc);

        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing exception
            return null;
        }
    }
}
