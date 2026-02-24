package com.example.mightymeals;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String name;
    private String price;
    private int imageResId;

    // Constructor to initialize the fields
    public Item(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Constructor to create an Item from a Parcel
    protected Item(Parcel in) {
        name = in.readString();
        price = in.readString();
        imageResId = in.readInt();
    }

    // CREATOR field required for Parcelable implementation
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    // Getter methods for Item properties
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Write the Item object data to a Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);        // Write the name to the parcel
        dest.writeString(price);       // Write the price to the parcel
        dest.writeInt(imageResId);     // Write the imageResId to the parcel
    }

    // Describe the contents (usually 0, no special objects)
    @Override
    public int describeContents() {
        return 0;
    }
}
