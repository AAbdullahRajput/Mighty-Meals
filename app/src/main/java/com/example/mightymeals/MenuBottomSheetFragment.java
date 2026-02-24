package com.example.mightymeals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mightymeals.Adapter.MenuAdapter;
import com.example.mightymeals.databinding.FragmentMenuBottomSheetBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBottomSheetFragment extends DialogFragment {

    private FragmentMenuBottomSheetBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout using the binding class
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false);

        // Create lists for menu data
        List<String> menuFoodName = Arrays.asList("Burger", "sandwich", "momo", "item", "sandwich", "momo");
        List<String> menuItemPrice = Arrays.asList("$5", "$6", "$8", "$9", "$10", "$10");
        List<Integer> menuImage = Arrays.asList(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6
        );

        // Create adapter and pass the lists
        MenuAdapter adapter = new MenuAdapter(
                new ArrayList<>(menuFoodName),
                new ArrayList<>(menuItemPrice),
                new ArrayList<>(menuImage)
        );

        // Set the RecyclerView layout manager and adapter
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.menuRecyclerView.setAdapter(adapter);

        // Return the root view
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clean up the binding when the view is destroyed
        binding = null;
    }

    // Static method to create a new instance of the fragment
    public static MenuBottomSheetFragment newInstance() {
        return new MenuBottomSheetFragment();
    }
}
