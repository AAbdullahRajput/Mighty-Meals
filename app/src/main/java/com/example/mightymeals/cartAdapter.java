package com.example.mightymeals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.CartViewHolder> {

    private final List<ItemEntity> cartItems;
    private final OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public cartAdapter(List<ItemEntity> cartItems, OnDeleteClickListener onDeleteClickListener) {
        this.cartItems = cartItems;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ItemEntity item = cartItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(item.getPrice());
        holder.imageView.setImageResource(item.getImageResId());

        holder.deleteButton.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView priceTextView;
        private final ImageView imageView;
        private final Button deleteButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.itemName);
            priceTextView = itemView.findViewById(R.id.itemPrice);
            imageView = itemView.findViewById(R.id.itemImage);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
