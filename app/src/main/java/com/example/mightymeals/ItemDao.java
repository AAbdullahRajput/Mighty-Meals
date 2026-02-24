package com.example.mightymeals;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ItemDao {
    @Insert
    void insert(ItemEntity item);

    @Query("SELECT * FROM cart_items")
    List<ItemEntity> getAllItems();

    @Query("DELETE FROM cart_items WHERE id = :itemId")
    void deleteById(int itemId);
}


