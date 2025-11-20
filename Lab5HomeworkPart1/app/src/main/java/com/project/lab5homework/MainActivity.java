package com.project.lab5homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editTextProduct = findViewById(R.id.editTextText);

        // USING INTERNAL MEMORY

        Button buttonAdd1 = findViewById(R.id.buttonAdd);
        buttonAdd1.setOnClickListener(v -> internalAdd(v, editTextProduct));

        Button buttonDelete1 = findViewById(R.id.buttonDelete);
        buttonDelete1.setOnClickListener(v -> internalDelete(v, editTextProduct.getText().toString()));


        Button buttonViewList = findViewById(R.id.buttonView);
        buttonViewList.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void internalAdd(View view, EditText editText) {
        try {
            String text = editText.getText().toString().trim();
            if (text.isEmpty()) return;

            // better way to open that file, it will create if deleted and append to it if exists
            FileOutputStream fileOutputStream = openFileOutput("list.csv", MODE_APPEND);
            fileOutputStream.write((text + "\n").getBytes());
            fileOutputStream.close();

            String toastText = "Added " + editText.getText().toString() + " to the list";
            Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();

            // resetting edit text
            editText.setText("");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void internalDelete(View view, String productName) {

        try {
            File listFile = new File(getFilesDir(), "list.csv");

            if (!listFile.exists()) {
                return;
            }

            // reading all lines
            List<String> lines = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(listFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            // reading and storing lines only if they do not match the string provided
            while ((line = reader.readLine()) != null) {
                if (!line.equals(productName)) {
                    lines.add(line);
                }
            }

            reader.close();

            // rewrite updated list
            FileOutputStream fileOutputStream = openFileOutput("list.csv", MODE_PRIVATE); //mode overwrite

            for (String l : lines) {
                fileOutputStream.write((l + "\n").getBytes());
            }

            fileOutputStream.close();

            String text = "Deleted " + productName + " from the list";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}