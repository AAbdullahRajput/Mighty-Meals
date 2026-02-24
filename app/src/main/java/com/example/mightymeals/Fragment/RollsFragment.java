package com.example.mightymeals.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mightymeals.ItemAdapter;
import com.example.mightymeals.AppDatabase;
import com.example.mightymeals.Item;
import com.example.mightymeals.ItemEntity;
import com.example.mightymeals.R;
import com.example.mightymeals.databinding.FragmentRollsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RollsFragment extends Fragment implements ItemAdapter.OnItemClickListener {

    private FragmentRollsBinding binding;
    private ArrayList<Item> items = new ArrayList<>();
    private AppDatabase database;

    public RollsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using ViewBinding
        binding = FragmentRollsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());

        // Initialize items for rolls
        initializeRollItems();

        // Set up RecyclerView
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Ensure the adapter is set up correctly
        ItemAdapter adapter = new ItemAdapter(items, this);
        binding.PopularRecyclerView.setAdapter(adapter);
    }

    private void initializeRollItems() {
        // Data for the RecyclerView
        List<String> rollNames = Arrays.asList("Mexican Rolls", "Veggie Rolls", "Dynamite Rolls", "California Rolls", "French Roll", "Parker Roll");
        List<String> prices = Arrays.asList("900Rs", "950Rs", "800Rs", "1300Rs", "700Rs", "300Rs");
        List<Integer> rollImages = Arrays.asList(
                R.drawable.mexican_rolls,  // Ensure this image exists in res/drawable
                R.drawable.veggie_rolls,   // Ensure this image exists in res/drawable
                R.drawable.dynamite_rolls, // Ensure this image exists in res/drawable
                R.drawable.california_rolls, // Ensure this image exists in res/drawable
                R.drawable.french_roll, // Ensure this image exists in res/drawable
                R.drawable.parker_roll // Ensure this image exists in res/drawable
        );

        // Add items manually
        for (int i = 0; i < rollNames.size(); i++) {
            items.add(new Item(rollNames.get(i), prices.get(i), rollImages.get(i)));
        }

        if (items.isEmpty()) {
            Toast.makeText(requireContext(), "No rolls found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Item item) {
        // Save item to Room database
        new Thread(() -> {
            // Insert item into Room database
            database.itemDao().insert(new ItemEntity(item.getName(), item.getPrice(), item.getImageResId()));
        }).start();

        Toast.makeText(requireActivity(), "Item Added To Cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks
    }
}
