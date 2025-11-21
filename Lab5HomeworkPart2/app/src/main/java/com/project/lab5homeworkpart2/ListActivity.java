package com.project.lab5homeworkpart2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private AppDatabase db;
    List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        Button buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(this::goBack);

        // fetching and viewing all items in database

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Ingredient").build();

        TextView textView = findViewById(R.id.textViewList);

        new Thread(() -> {
            ingredientList = db.ingredientDao().fetchAllIngredients();
            List<Ingredient> ingredientList = db.ingredientDao().fetchAllIngredients();

            // the textview has to be updated on the ui thread
            runOnUiThread(() -> {
                if (ingredientList == null || ingredientList.isEmpty()) {
                    textView.setText("List is empty vro...");
                    return;
                }

                StringBuilder builder = new StringBuilder();
                for (Ingredient ingredient : ingredientList) {
                    builder.append(ingredient.getName()).append("\n");
                }

                textView.setText(builder.toString());
            });
        }).start();

    }

    private void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}