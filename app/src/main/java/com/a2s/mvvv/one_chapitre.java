package com.a2s.mvvv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class one_chapitre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_chapitre);

        TextView TitleCours = findViewById(R.id.chapitres_titre);
        TextView resume  = findViewById(R.id.resume);
        TextView cours  = findViewById(R.id.cours);

        Intent intent = getIntent();


        String titreContent = intent.getStringExtra("titre");
        String resumeContent = intent.getStringExtra("resume");
        String coursContent = intent.getStringExtra("cours");

        TitleCours.setText(titreContent);
        resume.setText(resumeContent);
        cours.setText(coursContent);
        cours.setMovementMethod(new ScrollingMovementMethod());


    }


    private void getLevels(TextView TitleCours, TextView resume, TextView cours) {
        Call<List<Cours>> call = RetrofitClient.getInstance().getMyApi().getAllCours();
        call.enqueue(new Callback<List<Cours>>() {
            @Override
            public void onResponse(Call<List<Cours>> call, Response<List<Cours>> response) {
                List<Cours> LevelList = response.body();
                TitleCours.setText(LevelList.get(0).getTitre());
                resume.setText(LevelList.get(0).getResume());
                cours.setText(LevelList.get(0).getCours());
                cours.setMovementMethod(new ScrollingMovementMethod());
            }

            @Override
            public void onFailure(Call<List<Cours>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}