package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        multiplyBtn.setOnClickListener(v -> multiply(v, num1, num2, result));

        Button minusBtn = findViewById(R.id.buttonMinus);
        minusBtn.setOnClickListener(v -> minus(v, num1, num2, result));

        Button divideBtn = findViewById(R.id.buttonDivide);
        divideBtn.setOnClickListener(v -> divide(v, num1, num2, result));


        Button advBtn = findViewById(R.id.buttonAdv);
        advBtn.setOnClickListener(this::advancedCalc);
    }

    private void advancedCalc(View view) {
        Intent intent = new Intent(this, AdvancedCalc.class);
        startActivity(intent);
        finish();
    }

    private void divide(View v, EditText num1, EditText num2, TextView textView) {
        String first = num1.getText().toString().trim();
        String second = num2.getText().toString().trim();

        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(second)) {
            Toast.makeText(this, "You did not type in one or both numbers", Toast.LENGTH_LONG).show();
            return;
        }

        if (second.matches("0")) {
            Toast.makeText(this, "You can not divide by 0", Toast.LENGTH_LONG).show();
            return;
        }

        double number1 = Double.parseDouble(first);
        double number2 = Double.parseDouble(second);
        double result = number1 / number2;

        textView.setText(String.valueOf(result));
    }

    private void minus(View v, EditText num1, EditText num2, TextView textView) {
        String first = num1.getText().toString().trim();
        String second = num2.getText().toString().trim();

        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(second)) {
            Toast.makeText(this, "You did not type in one or both numbers", Toast.LENGTH_LONG).show();
            return;
        }

        double number1 = Double.parseDouble(first);
        double number2 = Double.parseDouble(second);
        double result = number1 - number2;

        textView.setText(String.valueOf(result));
    }

    private void multiply(View v, EditText num1, EditText num2, TextView textView) {
        String first = num1.getText().toString().trim();
        String second = num2.getText().toString().trim();

        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(second)) {
            Toast.makeText(this, "You did not type in one or both numbers", Toast.LENGTH_LONG).show();
            return;
        }

        double number1 = Double.parseDouble(first);
        double number2 = Double.parseDouble(second);
        double result = number1 * number2;

        textView.setText(String.valueOf(result));
    }

    private void addition(View v, EditText num1, EditText num2, TextView textView) {
        String first = num1.getText().toString().trim();
        String second = num2.getText().toString().trim();

        if (TextUtils.isEmpty(first) || TextUtils.isEmpty(second)) {
            Toast.makeText(this, "You did not type in one or both numbers", Toast.LENGTH_LONG).show();
            return;
        }

        double number1 = Double.parseDouble(first);
        double number2 = Double.parseDouble(second);
        double result = number1 + number2;

        textView.setText(String.valueOf(result));
    }


}