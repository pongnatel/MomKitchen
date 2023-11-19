package com.example.momkitchen.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.momkitchen.Adapter.RecyclerCateadapter;
import com.example.momkitchen.Adapter.RecyclerDataAdapter;
import com.example.momkitchen.Manager.MealManager;
import com.example.momkitchen.Model.MealModel;
import com.example.momkitchen.R;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    public CategoryFragment() {
        // Required empty public constructor
    }


    public static CategoryFragment newInstance(String param1, String param2) {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load meal data
        Map<String, List<MealModel>> mealsByCategory = MealManager.getMealsByCategories(
                requireContext(),
                "Beef", "Seafood", "Chicken", "Lamb", "Pork", "Goat"
        );

        String[] categories = {"Beef", "Seafood", "Chicken", "Lamb", "Pork", "Goat"};

        for (String category : categories) {
            List<MealModel> meals = mealsByCategory.get(category);

            // Skip categories with no meal data
            if (meals == null || meals.isEmpty()) {
                continue;
            }

            String id = category.toLowerCase(Locale.ROOT) + "_cate";
            int view_id = getContext().getResources().getIdentifier(id, "id", getContext().getPackageName());
            RecyclerView rvItems = view.findViewById(view_id);
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            rvItems.setLayoutManager(layoutManager);
            rvItems.setHasFixedSize(true);

            RecyclerCateadapter adapter = new RecyclerCateadapter(requireContext(), meals);
            rvItems.setAdapter(adapter);
        }
    }
}