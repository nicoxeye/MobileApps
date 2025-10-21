package com.example.calculator;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText num1 = findViewById(R.id.editTextNumberDecimal);
        EditText num2 = findViewById(R.id.editTextNumberDecimal2);
        TextView result = findViewById(R.id.textView2);
        
        Button plusBtn = findViewById(R.id.buttonPlus);
        plusBtn.setOnClickListener(v -> addition(v, num1, num2, result));

        Button multiplyBtn = findViewById(R.id.buttonMultiply);
        multiplyBtn.setOnClickListener(v -> addition(v, num1, num2, result));
    }

    private void addition(View v, EditText num1, EditText num2, TextView textView) {
        double number1 = Double.parseDouble(num1.getText().toString());
        double number2 = Double.parseDouble(num2.getText().toString());

        double result = number1 + number2;

        textView.setText(String.format(String.valueOf(result)));
    }


}