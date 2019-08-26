package com.example.week2_weekend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickDataBase(View view) {
        Intent databaseIntent = new Intent(this, DatabaseActivity.class);
        startActivity(databaseIntent);
    }
}
