package com.example.momkitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.momkitchen.Adapter.RecyclerDataAdapter;
import com.example.momkitchen.Adapter.RecyclerIngreAdapter;
import com.example.momkitchen.Adapter.ViewPagerAdapter;
import com.example.momkitchen.Model.MealModel;

public class Recipe extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Close the activity
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("mealModel")) {
            MealModel mealModel = intent.getParcelableExtra("mealModel");
            TextView meal_name = (TextView) findViewById(R.id.detail_meal_name);
            meal_name.setText(mealModel.getStrMeal());

            RecyclerView rvItems = (RecyclerView) findViewById(R.id.detail_meal_list_ingre);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvItems.setLayoutManager(layoutManager);
            rvItems.setHasFixedSize(true);
            rvItems.setAdapter(new RecyclerIngreAdapter(this, mealModel.getStrIngredients(), mealModel.getStrMeasurements()));

            ImageView image = (ImageView) findViewById(R.id.detail_meal_image);
            String id = "i" + mealModel.getIdMeal();
            int resourceId = this.getResources().getIdentifier(id, "drawable", this.getPackageName());
            if (resourceId != 0) {
                // Resource found, you can use resourceId
                Glide.with(this)
                        .load(resourceId)
                        .placeholder(R.drawable.beef)
                        .into(image);
            } else {
                // Resource not found, handle accordingly
                Glide.with(this)
                        .load(R.drawable.chicken)  // Provide a placeholder image
                        .into(image);
            }

            ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.detail_meal_list_instructions);
            viewPager2.setAdapter(new ViewPagerAdapter(this, mealModel.getStrInstructions()));

            Button buttonNext = findViewById(R.id.buttonNext);
            Button buttonPrevious = findViewById(R.id.buttonPrevious);

            if (mealModel.getStrYoutube() != null){
                Button buttonSource = findViewById(R.id.source);
                buttonSource.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String source = mealModel.getStrYoutube();
                        gotoUrl(source);
                    }
                });
            }

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager2.getCurrentItem() < viewPager2.getAdapter().getItemCount() - 1) {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                    }
                }
            });

            buttonPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager2.getCurrentItem() > 0) {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
                    }
                }
            });
        }
    }

    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}