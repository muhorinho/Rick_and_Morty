package com.muhammedbuga.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button characterButton = findViewById(R.id.characters_button);
        Button locationsButton = findViewById(R.id.locations_button);
        characterButton.setOnClickListener(v -> OpenActivity("character"));


        locationsButton.setOnClickListener(v -> OpenActivity("location"));

    }


    public void OpenActivity(String value){

        Intent intent = new Intent(this, ItemListActivity.class);
        intent.putExtra("type", value);
        startActivity(intent);
    }
}