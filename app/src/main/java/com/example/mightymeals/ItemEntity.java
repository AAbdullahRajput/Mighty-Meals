package com.example.mightymeals;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class ItemEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String price;
    private int imageResId;

    // Constructor excluding ID as it is auto-generated
    public ItemEntity(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getter and Setter for ID (Room uses this to handle auto-generation)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Price
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // Getter and Setter for Image Resource ID
    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
