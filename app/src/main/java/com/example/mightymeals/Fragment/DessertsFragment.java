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

import java.util.ArrayList;
import com.example.mightymeals.databinding.FragmentDessertsBinding;


public class DessertsFragment extends Fragment implements ItemAdapter.OnItemClickListener {

    private FragmentDessertsBinding binding;
    private ArrayList<Item> items = new ArrayList<>();
    private AppDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDessertsBinding.inflate(inflater, container, false);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());

        // Initialize items for desserts
        initializeDessertItems();

        // Set up RecyclerView
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Ensure the adapter is set up correctly
        ItemAdapter adapter = new ItemAdapter(items, this);
        binding.PopularRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    private void initializeDessertItems() {
        // Add items manually (check if these resources exist)
        items.add(new Item("Cheesecake", "1300Rs", R.drawable.cheesecake));
        items.add(new Item("Pastries", "200Rs", R.drawable.pastries));
        items.add(new Item("Ice Cream", "150Rs", R.drawable.icecream));
        items.add(new Item("Pie and Tarts", "500Rs", R.drawable.tart));
        items.add(new Item("Donuts", "250Rs", R.drawable.donots));
        items.add(new Item("HoneyComb", "350Rs", R.drawable.honeycomb));

        if (items.isEmpty()) {
            Toast.makeText(requireContext(), "No desserts found", Toast.LENGTH_SHORT).show();
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
