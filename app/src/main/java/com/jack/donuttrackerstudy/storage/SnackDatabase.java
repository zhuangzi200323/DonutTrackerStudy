package com.jack.donuttrackerstudy.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jack.donuttrackerstudy.model.Coffee;
import com.jack.donuttrackerstudy.model.Donut;

@Database(entities = {Donut.class, Coffee.class}, version = 1)
public abstract class SnackDatabase extends RoomDatabase {
    public abstract DonutDao donutDao();
    public abstract CoffeeDao coffeeDao();

    volatile static SnackDatabase instance = null;

    public static SnackDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (SnackDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), SnackDatabase.class, "donutTrackStudy")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
