package com.example.momkitchen.Utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonLoader {

    public static JSONArray loadJson(Context context, String file) {
        JSONArray jsonArray = null;

        try {
            InputStream inputStream = context.getAssets().open(file);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // fetch json file
            String json = new String(buffer, StandardCharsets.UTF_8);
            jsonArray = new JSONArray(json);

        } catch (Exception e) {
            Log.e("JsonLoader", "loadJson: error " + e);
        }

        return jsonArray;
    }
}
