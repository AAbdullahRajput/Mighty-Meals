package com.example.mightymeals.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mightymeals.Adapter.MenuAdapter;
import com.example.mightymeals.AppDatabase;
import com.example.mightymeals.Item;
import com.example.mightymeals.ItemEntity;
import com.example.mightymeals.R;
import com.example.mightymeals.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {

    private AppDatabase database;

    private FragmentSearchBinding binding;
    private MenuAdapter adapter;


    // Original menu data
    private final List<String> originalMenuFoodName = Arrays.asList(
            "Burger", "Noodles", "Pizza", "Macroni", "Ice Cream", "Sushi", "Salad", "Pasta", "Pizza", "Salad","Bratwurst Burger","Parker Roll"
    );

    private final List<String> originalMenuItemPrice = Arrays.asList(
            "$5", "$6", "$8", "$9", "$10", "$10", "$8", "$9", "$10", "$10","$13","$15"
    );

    private final List<Integer> originalMenuImage = Arrays.asList(
            R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4,
            R.drawable.menu5, R.drawable.menu6, R.drawable.menu7, R.drawable.menu8,
            R.drawable.menu9, R.drawable.menu10, R.drawable.bratwurst_burger, R.drawable.parker_roll
    );

    // Filtered menu data
    private final List<String> filteredMenuFoodName = new ArrayList<>();
    private final List<String> filteredMenuItemPrice = new ArrayList<>();
    private final List<Integer> filteredMenuImage = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up SearchView behavior
        setupSearchView();

        // Access SearchView's AutoCompleteTextView and set text color
        SearchView searchView = binding.searchView;

        // Access the SearchView's internal AutoCompleteTextView
        int textViewId = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        AutoCompleteTextView searchTextView = searchView.findViewById(textViewId);

        if (searchTextView != null) {
            // Set the query text color to black
            searchTextView.setTextColor(getContext().getResources().getColor(R.color.black)); // Set black color

            // Set the hint text color to gray
            searchTextView.setHintTextColor(getContext().getResources().getColor(R.color.gray)); // Set gray color
        }

        // Show all items in the menu initially
        showAllMenu();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        adapter = new MenuAdapter(filteredMenuFoodName, filteredMenuItemPrice, filteredMenuImage);
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        setupSearchView();
        showAllMenu();

        return binding.getRoot();
    }

    private void showAllMenu() {
        filteredMenuFoodName.clear();
        filteredMenuItemPrice.clear();
        filteredMenuImage.clear();
        filteredMenuFoodName.addAll(originalMenuFoodName);
        filteredMenuItemPrice.addAll(originalMenuItemPrice);
        filteredMenuImage.addAll(originalMenuImage);
        adapter.notifyDataSetChanged();
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterMenuItems(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMenuItems(newText);
                return true;
            }
        });
    }


    private void filterMenuItems(String query) {
        filteredMenuFoodName.clear();
        filteredMenuItemPrice.clear();
        filteredMenuImage.clear();

        for (int i = 0; i < originalMenuFoodName.size(); i++) {
            if (originalMenuFoodName.get(i).toLowerCase().contains(query.toLowerCase())) {
                filteredMenuFoodName.add(originalMenuFoodName.get(i));
                filteredMenuItemPrice.add(originalMenuItemPrice.get(i));
                filteredMenuImage.add(originalMenuImage.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }


}