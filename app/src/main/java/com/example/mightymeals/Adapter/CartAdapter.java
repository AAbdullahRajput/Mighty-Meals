package com.example.mightymeals.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mightymeals.databinding.CartItemBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<String> cartItems;
    private final List<String> cartPrices;
    private final List<Integer> cartImages;
    private int[] itemQuantities;

    public CartAdapter(List<String> cartItems, List<String> cartPrices, List<Integer> cartImages) {
        this.cartItems = cartItems;
        this.cartPrices = cartPrices;
        this.cartImages = cartImages;
        this.itemQuantities = new int[cartItems.size()];
        for (int i = 0; i < cartItems.size(); i++) {
            this.itemQuantities[i] = 1;  // Initialize quantities to 1
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // ViewHolder class for RecyclerView
    class CartViewHolder extends RecyclerView.ViewHolder {
        private final CartItemBinding binding;

        public CartViewHolder(@NonNull CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // Set up button listeners
            binding.minusButton.setOnClickListener(v -> decreaseQuantity(getAdapterPosition()));
            binding.plusButton.setOnClickListener(v -> increaseQuantity(getAdapterPosition()));
            binding.deleteButton.setOnClickListener(v -> deleteItem(getAdapterPosition()));
        }

        public void bind(int position) {
            binding.cartFoodName.setText(cartItems.get(position));
            binding.cartFoodPrice.setText(cartPrices.get(position));
            binding.cartImage.setImageResource(cartImages.get(position));
            binding.quantity.setText(String.valueOf(itemQuantities[position]));
        }
    }

    private void increaseQuantity(int position) {
        if (itemQuantities[position] < 10) { // Maximum quantity limit
            itemQuantities[position]++;
            notifyItemChanged(position);
        }
    }

    private void decreaseQuantity(int position) {
        if (itemQuantities[position] > 1) { // Minimum quantity limit
            itemQuantities[position]--;
            notifyItemChanged(position);
        }
    }

    private void deleteItem(int position) {
        cartItems.remove(position);
        cartPrices.remove(position);
        cartImages.remove(position);
        // Resize the itemQuantities array accordingly
        int[] newQuantities = new int[cartItems.size()];
        System.arraycopy(itemQuantities, 0, newQuantities, 0, cartItems.size());
        itemQuantities = newQuantities;
        notifyItemRemoved(position);
    }
}
