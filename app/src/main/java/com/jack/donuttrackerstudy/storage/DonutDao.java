package com.jack.donuttrackerstudy.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jack.donuttrackerstudy.model.Donut;

import java.util.List;

@Dao
public interface DonutDao {
    @Query("SELECT * FROM donut")
    LiveData<List<Donut>> getAll();

    @Query("SELECT * FROM donut WHERE id = :id")
    Donut get(long id);

    @Insert
    long insert(Donut donut);

    @Delete
    void delete(Donut donut);

    @Update
    void update(Donut donut);
}
