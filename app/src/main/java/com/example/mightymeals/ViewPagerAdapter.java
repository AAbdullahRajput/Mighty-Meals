package com.example.mightymeals;

import androidx.annotation.NonNull;
import androidx.collection.ScatterSet;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mightymeals.Fragment.DessertsFragment;
import com.example.mightymeals.Fragment.HomeFragment;
import com.example.mightymeals.Fragment.BurgerFragment;
import com.example.mightymeals.Fragment.PizzaFragment;
import com.example.mightymeals.Fragment.RollsFragment;
import com.example.mightymeals.Fragment.SaladFragment;
import com.example.mightymeals.Fragment.SushiFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull HomeFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new BurgerFragment();
            case 1:
                return new PizzaFragment();
            case 2:
                return new SushiFragment();
            case 3:
                return new RollsFragment();
            case 4:
                return new SaladFragment();
            case 5:
                return new DessertsFragment();
            default:
                return new BurgerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
