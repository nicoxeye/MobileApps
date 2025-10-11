package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Switch uppercase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::send);

        uppercase = findViewById(R.id.switch1);

        Button closeAppButton = findViewById(R.id.button2);
        closeAppButton.setOnClickListener(this::closeApp);
    }

    void send(View v){
        Intent intent = new Intent(this, WordView.class);

        EditText editText = findViewById(R.id.editTextText);
        String extra_msg = editText.getText().toString();

        if (uppercase.isChecked()){
            extra_msg = extra_msg.toUpperCase();
        }

        intent.putExtra("extra-msg", extra_msg);
        startActivity(intent);
        finish();
    }

    void closeApp(View v){
        finish();
    }
}