package com.example.momkitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.example.momkitchen.Adapter.RecyclerDataAdapter;
import com.example.momkitchen.Fragments.CategoryFragment;
import com.example.momkitchen.Fragments.HomeFragment;
import com.example.momkitchen.Fragments.InfoFragment;
import com.example.momkitchen.Manager.MealManager;
import com.example.momkitchen.Model.MealModel;
import com.example.momkitchen.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.category) {
                replaceFragment(new CategoryFragment());
            } else if (itemId == R.id.info) {
                replaceFragment(new InfoFragment());
            }
            return true;
        });

//        List<MealModel> allMeals = new ArrayList<>();
//        allMeals = MealManager.getAllMeals(this);
//
//        for (MealModel meal : allMeals) {
//            Log.i("Meal Tag", meal.getStrMeasurements()[2]);
//            break;
//        }
//
//        RecyclerView rvItems = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rvItems.setLayoutManager(layoutManager);
//        rvItems.setHasFixedSize(true);
//        rvItems.setAdapter(new RecyclerDataAdapter(this, allMeals));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}