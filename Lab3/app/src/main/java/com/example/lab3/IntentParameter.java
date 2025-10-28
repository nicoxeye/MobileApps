package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntentParameter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_parameter);

        Intent intent = getIntent();
        String parameter = intent.getStringExtra("extra-variable");

        /*
        also possible:
            Bundle b = getIntent().getExtras();
            String parameter = b.getString("extra-variable");
        have to check out the differences between the two
         */

        TextView textView = findViewById(R.id.textView);
        textView.setText(parameter);
    }
}