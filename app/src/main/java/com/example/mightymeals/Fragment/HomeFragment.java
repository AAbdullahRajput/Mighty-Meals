package com.example.mightymeals.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mightymeals.Adapter.PopularAdapter;
import com.example.mightymeals.MenuBottomSheetFragment;
import com.example.mightymeals.ViewPagerAdapter;
import com.example.mightymeals.databinding.FragmentHomeBinding;
import com.example.mightymeals.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {


    ViewPagerAdapter viewpageradapter;
    TabLayout NavigationBar;
    ViewPager2 ViewPager;
    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout using view binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        NavigationBar = view.findViewById(R.id.NavigationBar);
        ViewPager = view.findViewById(R.id.ViewPager);
        viewpageradapter = new ViewPagerAdapter(this);
        ViewPager.setAdapter(viewpageradapter);


        NavigationBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                ViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // This method is called when a tab is unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // This method is called when a tab is reselected
            }
        });

        ViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                NavigationBar.getTabAt(position).select();
            }
        });

        // Set up image slider
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        ImageSlider imageSlider = binding.imageSlider;
        imageSlider.setImageList(imageList);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int position) {
            }

            @Override
            public void onItemSelected(int position) {
                String itemMessage = "Selected Image " + position;
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show();
            }
        });
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks
    }
}
