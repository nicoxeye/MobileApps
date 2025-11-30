package com.project.lab6homework;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private TextView textView;
    String ACTION_BUTTON_CLICKED = "BUTTON CLICKED";
    IntentFilter customIntentAction = new IntentFilter(ACTION_BUTTON_CLICKED);

    private final AirplaneModeReceiver airplaneModeReceiver = new AirplaneModeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                textView.setText("Custom receiver!");
                Log.d("CUSTOM RECEIVER: ", "Button Clicked");
            }
        };

        // sending the broadcast AFTER clicking the button
        button.setOnClickListener(v -> {
            Intent intent = new Intent(ACTION_BUTTON_CLICKED);
            sendBroadcast(intent);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        // registering both broadcasts
        registerReceiver(broadcastReceiver,
                new IntentFilter(customIntentAction), RECEIVER_EXPORTED);
        registerReceiver(airplaneModeReceiver,
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
    }


    @Override
    public void onPause() {
        unregisterReceiver(broadcastReceiver);
        unregisterReceiver(airplaneModeReceiver);
        super.onPause();
    }

}