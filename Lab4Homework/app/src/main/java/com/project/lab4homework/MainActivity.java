package com.project.lab4homework;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.silence) {
            Toast.makeText(this, "Silencing Phone...", Toast.LENGTH_LONG).show();
            AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        else if (id == R.id.close) {
            Toast.makeText(this, "Closing App...", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (id == R.id.author) {
            Toast.makeText(this, "Author Information", Toast.LENGTH_LONG).show();
            TextView textView = findViewById(R.id.hello);
            final EditText editText = new EditText(this);
            new AlertDialog.Builder(this)
                    .setTitle("Author")
                    .setView(editText)
                    .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String text = editText.getText().toString();
                    textView.setText("App made by " + text);
                }
            })
                    .create()
                    .show();
        }
        else if (id == R.id.run){
            Toast.makeText(this, "Run options...", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.maps){
            Toast.makeText(this, "Opening Maps...", Toast.LENGTH_LONG).show();
            Uri geoUri = Uri.parse("geo:0,0");
            Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
            startActivity(intent);
        }
        else if (id == R.id.search) {
            Toast.makeText(this, "Opening Google...", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("https://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        return true;
    }

}