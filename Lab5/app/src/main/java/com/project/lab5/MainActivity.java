package com.project.lab5;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // PREFERENCES
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(this::preference);

        // SQLITE
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this::sqlite);

        // INTERNAL MEMORY
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this::internalMemory);

        // ROOM DATABASE

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "workers").build();

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this::addWorker);
    }

    private void preference(View view) {
        // preferences dependency
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences (MainActivity.this);
        //editing the default preferences
        SharedPreferences.Editor myEditor = myPreferences.edit();

        // access is done by taking the keys and then the value is accessed
        // works similarly like dictionary
        myEditor.putString( "KeyString", "String value");
        myEditor.putBoolean("KeyBool", false);
        myEditor.putInt("KeyInt", 54);

        myEditor.commit();

        //Consider using apply() instead;
        // commit writes its data to persistent storage immediately, whereas apply will handle it in the background
        //myEditor.apply();

        // accessing the preferences value
        String valueString = myPreferences.getString("KeyString", "Unknown");
        Log.d("KeyString", valueString);
    }

    private void sqlite(View view) {
        SQLiteDatabase myDB = openOrCreateDatabase("my.db", MODE_PRIVATE, null);

        //creation of the database
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS firstBase (name VARCHAR(200), age INT, number INT)"
            );

        ContentValues row1 = new ContentValues();

        row1.put("name", "Marek");
        row1.put("age", 22);
        row1.put("number", 1);

        ContentValues row2 = new ContentValues();
        row2.put("name", "Agata");
        row2.put("age", 19);
        row2.put("number", 2);

        // nullColumnHack -> what will be n the colum if value is missing
        myDB.insert("firstBase", null, row1);
        myDB.insert("firstBase", null, row2);

        //object cursor -> rawQuery like (select * from database)
        Cursor myCursor = myDB.rawQuery("select name, age, number from firstBase", null);

        // while theres data in the cursor
        while(myCursor.moveToNext()) {
            String name = myCursor.getString(0);
            int age = myCursor.getInt(1);
            int number = myCursor.getInt(2);
            Log.d("Name:", name);
        }

        myCursor.close();
        myDB.close();
    }

    // creates files in memory
    // good for things like jsons, csv, xlsx etc since they're small
    private void internalMemory(View view) {

        File internalStorageDir = getFilesDir();
        File myfile = new File(internalStorageDir, "myfile.csv");
        Log.d("directory: ", "" + internalStorageDir);

        try {
            // WRITING TO FILE
            FileOutputStream fos = new FileOutputStream(myfile);
            fos.write("InternalMemoryProgram".getBytes());
            fos.close();

            // READING FROM FILE
            FileInputStream fis = new FileInputStream(myfile);
            int i = 0;
            while ((i = fis.read()) != -1) {
                Log.d("Reading: ", "" + (char) i);
            }
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addWorker(View view) {

        Worker worker1 = new Worker("Jan", "Kowalski", 8000);

        new Thread(() -> {
            db.workerDao.insert(worker1);
            Log.d("Added worker", "Name: " + worker1.getName() +
                    ", Last name: " + worker1.getLastName() + " and paycheck:"
                    + worker1.getPaycheck() + " their id " +
                    worker1.getId());
        }
        );
    }

}