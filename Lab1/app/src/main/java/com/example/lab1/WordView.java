package com.example.lab1;

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

public class WordView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_word_view);

        Intent intent = getIntent();
        String message = intent.getStringExtra("extra-msg");
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        Button backButton = findViewById(R.id.button4);
        backButton.setOnClickListener(this::backToMainScreen);

        Button closeAppButton = findViewById(R.id.button3);
        closeAppButton.setOnClickListener(this::closeApp);
    }

    void backToMainScreen(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void closeApp(View v){
        finish();
    }

}