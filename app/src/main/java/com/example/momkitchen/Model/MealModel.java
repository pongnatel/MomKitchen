package com.example.momkitchen.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MealModel implements Parcelable {
    private final String idMeal;
    private final String strMeal;
    private final String strArea;
    private final String strCategory;
    private final String[] strInstructions;
    private final String strMealThumb;
    private final String strTags;
    private final String strYoutube;
    private final String[] strIngredients;
    private final String[] strMeasurements;
    private final String strSource;

    public String getStrArea() {
        return strArea;
    }

    public String getStrCategory() {
        return strCategory;
    }

    // Constructor
    public MealModel(String idMeal, String strMeal, String strArea, String strCategory, String[] strInstructions,
                     String strMealThumb, String strTags, String strYoutube, String[] strIngredients,
                     String[] strMeasurements, String strSource) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strArea = strArea;
        this.strCategory = strCategory;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strTags = strTags;
        this.strYoutube = strYoutube;
        this.strIngredients = strIngredients;
        this.strMeasurements = strMeasurements;
        this.strSource = strSource;
    }

    // Parcelable implementation
    protected MealModel(Parcel in) {
        idMeal = in.readString();
        strMeal = in.readString();
        strArea = in.readString();
        strCategory = in.readString();
        strInstructions = in.createStringArray();
        strMealThumb = in.readString();
        strTags = in.readString();
        strYoutube = in.readString();
        strIngredients = in.createStringArray();
        strMeasurements = in.createStringArray();
        strSource = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idMeal);
        dest.writeString(strMeal);
        dest.writeString(strArea);
        dest.writeString(strCategory);
        dest.writeStringArray(strInstructions);
        dest.writeString(strMealThumb);
        dest.writeString(strTags);
        dest.writeString(strYoutube);
        dest.writeStringArray(strIngredients);
        dest.writeStringArray(strMeasurements);
        dest.writeString(strSource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MealModel> CREATOR = new Creator<MealModel>() {
        @Override
        public MealModel createFromParcel(Parcel in) {
            return new MealModel(in);
        }

        @Override
        public MealModel[] newArray(int size) {
            return new MealModel[size];
        }
    };

    // Getters
    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String[] getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String[] getStrIngredients() {
        return strIngredients;
    }

    public String[] getStrMeasurements() {
        return strMeasurements;
    }

    public String getStrSource() {
        return strSource;
    }

    public int getNumOfIngredient() {
        return strIngredients.length;
    }
}
