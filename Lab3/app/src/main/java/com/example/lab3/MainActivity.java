package com.example.lab3;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> simpleIntentionSwitch());

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> simpleIntentionWithParameter());

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> website());

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> googleMaps());

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> calendar());

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> camera());
    }

    void camera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    void calendar() {
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, Calendar.getInstance().getTimeInMillis());

        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
        startActivity(intent);
    }


    void simpleIntentionSwitch() {
        Intent intent = new Intent(this, Intention.class);
        startActivity(intent);
    }

    void simpleIntentionWithParameter() {
        Intent intent = new Intent(this, IntentionParameter.class);
        intent.putExtra("extra-variable", "message delivered to new intent");
        startActivity(intent);
    }

    void website() {
        Uri uri = Uri.parse("https://www.ue.katowice.pl/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    void googleMaps() {
        Uri geoUri = Uri.parse("geo:50.26081,19.04552");
        Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
        startActivity(intent);
    }

}