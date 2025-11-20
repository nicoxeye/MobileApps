package com.project.lab5homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        TextView textView = findViewById(R.id.textViewList);
        Button buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonGoBack.setOnClickListener(this::goBack);

        try {
            File internalStorageDir = getFilesDir();
            File listFile = new File(internalStorageDir, "list.csv");

            //it kept crashingndfgjk2uqwiorjs
            if (!listFile.exists()) {
                textView.setText("List is empty vro...");
                return;
            }

            StringBuilder builder = getStringBuilder(listFile);

            textView.setText(builder.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder getStringBuilder(File listFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(listFile);

        // reading all lines
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        fileInputStream.close();

        // creating a string builder to get normal lines of text
        // it returned a list previously
        StringBuilder builder = new StringBuilder();
        for (String l : lines) {
            builder.append(l).append("\n");
        }
        return builder;
    }

    private void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}