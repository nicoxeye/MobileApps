package com.project.lab5homeworkpart2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("DELETE FROM Ingredient WHERE name = :name")
    void deleteByName(String name);

    @Query("SELECT * FROM Ingredient")
    List<Ingredient> fetchAllIngredients();

}
