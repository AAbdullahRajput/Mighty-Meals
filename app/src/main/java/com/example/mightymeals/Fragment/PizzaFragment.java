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
import com.example.mightymeals.databinding.FragmentPizzaBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaFragment extends Fragment implements ItemAdapter.OnItemClickListener {

    private FragmentPizzaBinding binding;
    private ArrayList<Item> items = new ArrayList<>();
    private AppDatabase database;

    public PizzaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using ViewBinding
        binding = FragmentPizzaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());

        // Initialize items for pizzas
        initializePizzaItems();

        // Set up RecyclerView
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Ensure the adapter is set up correctly
        ItemAdapter adapter = new ItemAdapter(items, this);
        binding.PopularRecyclerView.setAdapter(adapter);
    }

    private void initializePizzaItems() {
        // Data for the RecyclerView
        List<String> pizzaNames = Arrays.asList("Veggie Pizza", "California Pizza", "Carbonara Pizza", "Margherita Pizza", "Tandoori Chicken","Buffalo Pizzas");
        List<String> prices = Arrays.asList("1300Rs", "1200Rs", "1400Rs", "1600Rs", "1800Rs","2000RS");
        List<Integer> pizzaImages = Arrays.asList(
                R.drawable.veggie_pizza,  // Ensure this image exists in res/drawable
                R.drawable.california_pizza,  // Ensure this image exists in res/drawable
                R.drawable.carbonara_pizza,         // Ensure this image exists in res/drawable
                R.drawable.margherita_pizza, // Ensure this image exists in res/drawable
                R.drawable.tandoori_pizza,
                R.drawable.buffalo_pizzas// Ensure this image exists in res/drawable
        );

        // Add items manually
        for (int i = 0; i < pizzaNames.size(); i++) {
            items.add(new Item(pizzaNames.get(i), prices.get(i), pizzaImages.get(i)));
        }

        if (items.isEmpty()) {
            Toast.makeText(requireContext(), "No pizzas found", Toast.LENGTH_SHORT).show();
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
