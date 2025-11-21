package com.project.lab5homeworkpart2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ingredient.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredientDao ingredientDao();
}
