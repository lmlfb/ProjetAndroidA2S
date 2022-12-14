package com.a2s.mvvv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_app extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

        Button ChapitreButton = findViewById(R.id.Chapitres);
        Button ExerciceButton = findViewById(R.id.Exercice);

        ChapitreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotToExerciceList = new Intent(getApplicationContext(),PageChapitreList.class);
                startActivity(gotToExerciceList);
                finish();
            }
        });

        ExerciceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotToChapitreList = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(gotToChapitreList);
                finish();
            }
        });
    }
}