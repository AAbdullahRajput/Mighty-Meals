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

import com.example.mightymeals.Item;
import com.example.mightymeals.ItemAdapter;
import com.example.mightymeals.AppDatabase;
import com.example.mightymeals.ItemEntity;
import com.example.mightymeals.R;
import com.example.mightymeals.databinding.FragmentSaladBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaladFragment extends Fragment implements ItemAdapter.OnItemClickListener {

    private FragmentSaladBinding binding;
    private ArrayList<Item> items = new ArrayList<>();
    private AppDatabase database;

    public SaladFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using ViewBinding
        binding = FragmentSaladBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());

        // Initialize items for salads
        initializeSaladItems();

        // Set up RecyclerView
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Ensure the adapter is set up correctly
        ItemAdapter adapter = new ItemAdapter(items, this);
        binding.PopularRecyclerView.setAdapter(adapter);
    }

    private void initializeSaladItems() {
        // Data for the RecyclerView
        List<String> saladNames = Arrays.asList("Ceaser Salad", "Caprese Salad", "Cobb Salad", "Fattoush Salad", "Mediterranean Salad", "Greek Salad");
        List<String> prices = Arrays.asList("350Rs", "400Rs", "800Rs", "900Rs", "1000Rs", "1200Rs");
        List<Integer> saladImages = Arrays.asList(
                R.drawable.ceaser_salad,  // Ensure these images exist in res/drawable
                R.drawable.caprese_salad,
                R.drawable.cobb_salad,
                R.drawable.fattoush_salad,
                R.drawable.mediterranean_zucchini_salad,
                R.drawable.greek_salad
        );

        // Add items manually
        for (int i = 0; i < saladNames.size(); i++) {
            items.add(new Item(saladNames.get(i), prices.get(i), saladImages.get(i)));
        }

        if (items.isEmpty()) {
            Toast.makeText(requireContext(), "No salads found", Toast.LENGTH_SHORT).show();
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
