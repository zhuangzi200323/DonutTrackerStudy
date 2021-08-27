/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jack.donuttrackerstudy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class holds the data that we are tracking for each cup of coffee: its name, a description,
 * and a rating.
 */
@Entity
public class Coffee {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String description;
    public float rating;

    public Coffee(long id, String name, String description, float rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}
