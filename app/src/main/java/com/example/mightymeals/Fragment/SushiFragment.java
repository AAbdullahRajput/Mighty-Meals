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
import com.example.mightymeals.databinding.FragmentSushiBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SushiFragment extends Fragment implements ItemAdapter.OnItemClickListener {

    private FragmentSushiBinding binding;
    private ArrayList<Item> items = new ArrayList<>();
    private AppDatabase database;

    public SushiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout using ViewBinding
        binding = FragmentSushiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());

        // Initialize items for sushi
        initializeSushiItems();

        // Set up RecyclerView
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Ensure the adapter is set up correctly
        ItemAdapter adapter = new ItemAdapter(items, this);
        binding.PopularRecyclerView.setAdapter(adapter);
    }

    private void initializeSushiItems() {
        // Data for the RecyclerView
        List<String> sushiNames = Arrays.asList("California ", "Tuna Nigiri", "Salmon Sashimi", "Toro Sushi", "Gunkan Maki Sushi", "Futomaki Sushi");
        List<String> prices = Arrays.asList("2200Rs", "1200Rs", "1200Rs", "1300Rs", "1700Rs", "1900Rs");
        List<Integer> sushiImages = Arrays.asList(
                R.drawable.sushi,  // Ensure this image exists in res/drawable
                R.drawable.tuna_nigiri,      // Ensure this image exists in res/drawable
                R.drawable.salmon,           // Ensure this image exists in res/drawable
                R.drawable.toro_sushi,     // Ensure this image exists in res/drawable
                R.drawable.gunkan_maki_sushi,     // Ensure this image exists in res/drawable
                R.drawable.futomaki_sushi       // Ensure this image exists in res/drawable
        );

        // Add items manually
        for (int i = 0; i < sushiNames.size(); i++) {
            items.add(new Item(sushiNames.get(i), prices.get(i), sushiImages.get(i)));
        }

        if (items.isEmpty()) {
            Toast.makeText(requireContext(), "No sushi found", Toast.LENGTH_SHORT).show();
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
