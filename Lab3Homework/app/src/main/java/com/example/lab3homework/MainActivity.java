package com.example.lab3homework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText typePlace = findViewById(R.id.editTextText);
        Button send = findViewById(R.id.button);

        send.setOnClickListener(v -> sendToPlace(typePlace));
    }

    void sendToPlace(EditText typePlace) {
        String place = typePlace.getText().toString().trim();
        Uri mapIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(place));

        Intent intent = new Intent(Intent.ACTION_VIEW, mapIntentUri);
        startActivity(intent);
    }
}