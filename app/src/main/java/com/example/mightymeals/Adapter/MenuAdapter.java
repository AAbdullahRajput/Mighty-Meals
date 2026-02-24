package com.example.mightymeals.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mightymeals.databinding.MenuItemBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<String> menuItemsName;
    private List<String> menuItemPrice;
    private List<Integer> menuImages;

    // Constructor to initialize the lists and image array
    public MenuAdapter(List<String> menuItemsName, List<String> menuItemPrice, List<Integer> menuImages) {
        this.menuItemsName = menuItemsName;
        this.menuItemPrice = menuItemPrice;
        this.menuImages = menuImages;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout using the binding class
        MenuItemBinding binding = MenuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.bind(position); // Bind the data for the item at the given position
    }

    @Override
    public int getItemCount() {
        return menuItemsName.size(); // Return the size of the menu list
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private final MenuItemBinding binding;

        public MenuViewHolder(MenuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            binding.menuFoodName.setText(menuItemsName.get(position));
            binding.menuPrice.setText(menuItemPrice.get(position));
            binding.menuImage.setImageResource(menuImages.get(position));
        }
    }
}
