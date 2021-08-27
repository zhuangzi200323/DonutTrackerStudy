package com.jack.donuttrackerstudy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Donut {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String description;
    public float rating;

    public Donut(long id, String name, String description, float rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}
