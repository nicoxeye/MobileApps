package com.project.lab5homeworkpart2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editTextProduct = findViewById(R.id.editTextText);

        // USING ROOM DB

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Ingredient").build();

        Button buttonAdd1 = findViewById(R.id.buttonAdd);
        buttonAdd1.setOnClickListener(v -> addIngredient(v, editTextProduct));

        Button buttonDelete1 = findViewById(R.id.buttonDelete);
        buttonDelete1.setOnClickListener(v -> deleteIngredientByName(v, editTextProduct));


        Button buttonViewList = findViewById(R.id.buttonView);
        buttonViewList.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void addIngredient(View v, EditText editText) {
        Ingredient ingredient = new Ingredient(editText.getText().toString());
        new Thread(() -> db.ingredientDao().insert(ingredient)).start();

        String toastText = "Added " + editText.getText().toString() + " to the list";
        Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
    }

    private void deleteIngredientByName(View v, EditText editTextProduct) {
        String name = editTextProduct.getText().toString();
        new Thread(() -> db.ingredientDao().deleteByName(name)).start();

        String text = "Deleted " + name + " from the list";
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}