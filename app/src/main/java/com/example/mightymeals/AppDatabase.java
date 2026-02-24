package com.example.mightymeals;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ItemEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // Singleton instance
    private static volatile AppDatabase instance;

    // Abstract method to access the DAO
    public abstract ItemDao itemDao();

    // Singleton pattern to get the instance of the database
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "cart_database")
                            .fallbackToDestructiveMigration() // In case of schema change
                            .build();
                }
            }
        }
        return instance;
    }
}
