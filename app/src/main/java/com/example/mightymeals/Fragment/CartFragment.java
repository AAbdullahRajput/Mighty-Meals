package com.example.mightymeals.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mightymeals.cartAdapter; // Ensure this is the correct adapter package
import com.example.mightymeals.R;
import com.example.mightymeals.AppDatabase;
import com.example.mightymeals.ItemEntity;
import com.example.mightymeals.databinding.FragmentCartBinding;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private AppDatabase database;
    private cartAdapter adapter;
    private List<ItemEntity> cartItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout with ViewBinding
        binding = FragmentCartBinding.inflate(inflater, container, false);

        // Initialize Room database
        database = AppDatabase.getInstance(requireContext());

        // Set up RecyclerView
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Load items from the database and set up the adapter
        loadCartItems();

        return binding.getRoot();
    }

    private void loadCartItems() {
        new Thread(() -> {
            // Fetch items from Room database
            List<ItemEntity> items = database.itemDao().getAllItems();

            // Update cartItems on the main thread
            requireActivity().runOnUiThread(() -> {
                cartItems.clear();  // Clear any previous data
                cartItems.addAll(items);  // Add fetched items
                adapter = new cartAdapter(cartItems, position -> {
                    // Delete item from the database
                    deleteCartItem(position);
                });
                binding.cartRecyclerView.setAdapter(adapter);
            });
        }).start();
    }

    private void deleteCartItem(int position) {
        new Thread(() -> {
            // Remove item from the database
            database.itemDao().deleteById(cartItems.get(position).getId());
            cartItems.remove(position);

            // Update RecyclerView on the main thread
            requireActivity().runOnUiThread(() -> adapter.notifyItemRemoved(position));
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Avoid memory leaks
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }
}
