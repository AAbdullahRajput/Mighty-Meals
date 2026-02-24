package com.example.mightymeals.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mightymeals.ItemAdapter;
import com.example.mightymeals.AppDatabase;
import com.example.mightymeals.Item;
import com.example.mightymeals.ItemEntity;
import com.example.mightymeals.R;
import com.example.mightymeals.databinding.FragmentBurgerBinding;

import java.util.ArrayList;

public class BurgerFragment extends Fragment implements ItemAdapter.OnItemClickListener {

    private FragmentBurgerBinding binding;
    private ArrayList<Item> items = new ArrayList<>();
    private AppDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBurgerBinding.inflate(inflater, container, false);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());


        // Initialize items
        items.add(new Item("Crunch", "$5", R.drawable.big_mac_burger));
        items.add(new Item("Aussie Burger", "$8", R.drawable.aussie_burger));
        items.add(new Item("Avocado Burger", "$7", R.drawable.avocado_burger));
        items.add(new Item("Bratwurst Burger", "$4", R.drawable.bratwurst_burger));
        items.add(new Item("Banh Mic Burger", "$3", R.drawable.banh_mic_burger));
        items.add(new Item("Big Mac Burger", "$3", R.drawable.big_mac_burger));

        // Set up RecyclerView
        binding.PopularRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        ItemAdapter adapter = new ItemAdapter(items, this);
        binding.PopularRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onItemClick(Item item) {
        // Save item to Room database
         new Thread(() -> {
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
