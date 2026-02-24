package com.example.mightymeals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final List<String> foodNames;
    private final List<String> prices;

    public FoodAdapter(List<String> foodNames, List<String> prices) {
        this.foodNames = foodNames;
        this.prices = prices;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.foodNameTextView.setText(foodNames.get(position));
        holder.priceTextView.setText(prices.get(position));
    }

    @Override
    public int getItemCount() {
        return foodNames.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView, priceTextView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodName);
            priceTextView = itemView.findViewById(R.id.price);
        }
    }
}
