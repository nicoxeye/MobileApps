package com.example.converter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    double USD = 3.99;
    double EUR = 4.32;
    double CHF = 4.53;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText howMuchConvert = findViewById(R.id.editTextText2);
        TextView result = findViewById(R.id.textView2);
        Button eurButton = findViewById(R.id.button1);
        Button usdButton = findViewById(R.id.button2);
        Button chfButton = findViewById(R.id.button3);

        eurButton.setOnClickListener(v -> euroConversion(v, howMuchConvert, result));
        usdButton.setOnClickListener(v -> usdConversion(v, howMuchConvert, result));
        chfButton.setOnClickListener(v -> chfConversion(v, howMuchConvert, result));
    }

    private void euroConversion(View view, EditText howMuch, TextView textViewResult) {
        double amount = Double.parseDouble(howMuch.getText().toString());
        double result = amount * EUR;
        textViewResult.setText(String.format(String.valueOf(result)));
    }

    private void usdConversion(View view, EditText howMuch, TextView textViewResult) {
        double amount = Double.parseDouble(howMuch.getText().toString());
        double result = amount * USD;
        textViewResult.setText(String.format(String.valueOf(result)));
    }

    private void chfConversion(View view, EditText howMuch, TextView textViewResult) {
        double amount = Double.parseDouble(howMuch.getText().toString());
        double result = amount * CHF;
        textViewResult.setText(String.format(String.valueOf(result)));
    }


}