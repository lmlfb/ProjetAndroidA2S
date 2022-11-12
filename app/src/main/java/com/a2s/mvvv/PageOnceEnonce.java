package com.a2s.mvvv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageOnceEnonce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_exercice);
        Intent intent = getIntent();
        int selectedExoLvl = Integer.valueOf(intent.getStringExtra("selected"));
        TextView exo_titre = findViewById(R.id.exo_titre);
        //exo_titre.setText(String.valueOf(selectedExoLvl));
        // Toast.makeText(getApplicationContext(), String.valueOf(selectedExoLvl),Toast.LENGTH_LONG).show();
         getEnonce(exo_titre, selectedExoLvl);



    }

    private void getEnonce(TextView exo_titre, int selectedExoLvl) {
        Call<List<Enonce>> call = RetrofitClient.getInstance().getMyApi().getSelectedLvlById(selectedExoLvl);
        call.enqueue(new Callback<List<Enonce>>() {
            @Override
            public void onResponse(Call<List<Enonce>> call, Response<List<Enonce>> response) {
                List<Enonce> Enoncelist = response.body();
                exo_titre.setText(Enoncelist.get(0).getTitre());
  }

            @Override
            public void onFailure(Call<List<Enonce>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });
    }
}