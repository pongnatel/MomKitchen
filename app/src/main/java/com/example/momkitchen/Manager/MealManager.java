package com.example.momkitchen.Manager;

import android.content.Context;

import com.example.momkitchen.Fragments.HomeFragment;
import com.example.momkitchen.Model.MealModel;
import com.example.momkitchen.Utilities.JsonLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MealManager {

    public static List<MealModel> getAllMeals(Context context) {
        List<MealModel> allMeals = new ArrayList<>();

        try {
            String file = "meals.json";
            JSONArray jsonArray = JsonLoader.loadJson(context, file);
            int max = jsonArray.length();

            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MealModel meal = createMealModel(jsonObject);
                allMeals.add(meal);
            }
        } catch (Exception e) {
            // Handle exception or log error
            e.printStackTrace();
        }

        return allMeals;
    }

    public static List<MealModel> getMealsByCategory(Context context, String category) {
        List<MealModel> meals = new ArrayList<>();

        try {
            String file = "meals.json";
            JSONArray jsonArray = JsonLoader.loadJson(context, file);
            int max = jsonArray.length();

            for (int i = 0; i < max; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String mealCategory = jsonObject.getString("strCategory");

                if (mealCategory.equals(category)) {
                    MealModel meal = createMealModel(jsonObject);
                    meals.add(meal);
                }
            }
        } catch (Exception e) {
            // Handle exception or log error
            e.printStackTrace();
        }

        return meals;
    }

    private static MealModel createMealModel(JSONObject jsonObject) {
        try {
            String idMeal = jsonObject.getString("idMeal");
            String strMeal = jsonObject.getString("strMeal");
            String strArea = jsonObject.getString("strArea");
            String strCategory = jsonObject.getString("strCategory");
            String strMealThumb = jsonObject.getString("strMealThumb");
            String strTags = jsonObject.optString("strTags", ""); // Handle null case
            String strYoutube = jsonObject.optString("strYoutube", ""); // Handle null case
            String strSource = jsonObject.optString("strSource", ""); // Handle null case

            // Convert JSONArray to array of Strings for strInstructions
            JSONArray instructionsArray = jsonObject.getJSONArray("strInstructions");
            String[] strInstructions = new String[instructionsArray.length()];
            for (int j = 0; j < instructionsArray.length(); j++) {
                strInstructions[j] = instructionsArray.getString(j);
            }

            // Convert JSONArray to array of Strings for strIngredients
            JSONArray ingredientsArray = jsonObject.getJSONArray("strIngredients");
            JSONArray measurementArray = jsonObject.getJSONArray("strMeasurements");
            String[] strIngredients = new String[ingredientsArray.length()];
            String[] strMeasurements = new String[ingredientsArray.length()]; // Assuming lengths match

            for (int k = 0; k < ingredientsArray.length(); k++) {
                strIngredients[k] = ingredientsArray.getString(k);
                strMeasurements[k] = measurementArray.getString(k); // Assuming lengths match
            }

            return new MealModel(idMeal, strMeal, strArea, strCategory, strInstructions, strMealThumb, strTags, strYoutube,
                    strIngredients, strMeasurements, strSource);

        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing exception
            return null;
        }
    }


}
