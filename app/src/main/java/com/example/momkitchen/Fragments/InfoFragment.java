package com.example.momkitchen.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.momkitchen.Adapter.RecyclerDataAdapter;
import com.example.momkitchen.Adapter.ViewPagerAdapter;
import com.example.momkitchen.Adapter.ViewPagerInfo;
import com.example.momkitchen.Manager.InfoManager;
import com.example.momkitchen.Manager.MealManager;
import com.example.momkitchen.Model.InfoModel;
import com.example.momkitchen.Model.MealModel;
import com.example.momkitchen.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(String param1, String param2) {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load meal data
        List<InfoModel> allInfo = InfoManager.getAllSections(requireContext());

        ViewPager2 viewPager2 = (ViewPager2) getView().findViewById(R.id.info_section);
        viewPager2.setAdapter(new ViewPagerInfo(this.getContext(), allInfo));

        Button buttonNext = getView().findViewById(R.id.info_next);
        Button buttonPrevious = getView().findViewById(R.id.info_previous);

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