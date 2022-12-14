package com.a2s.mvvv;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageChapitreList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapitre_list);
        ListView mylistView = findViewById(R.id.listViewLevel);

        getLevels(mylistView);

    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(PageChapitreList.this,menu_app.class));

    }
    private void getLevels(ListView listView) {
        Call<List<Cours>> call = RetrofitClient.getInstance().getMyApi().getAllCours();
        call.enqueue(new Callback<List<Cours>>() {
            @Override
            public void onResponse(Call<List<Cours>> call, Response<List<Cours>> response) {
                List<Cours> LevelList = response.body();
                String[] levelstr = new String[LevelList.size()];
                for (int i = 0; i < LevelList.size(); i++) {
                    levelstr[i] = String.valueOf(LevelList.get(i).getTitre());
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, levelstr));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getApplicationContext(), "position" + String.valueOf(id), Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(view.getContext(), one_chapitre.class);
                        myIntent.putExtra("titre",String.valueOf(LevelList.get(position).getTitre()));
                        myIntent.putExtra("resume",String.valueOf(LevelList.get(position).getResume()));
                        myIntent.putExtra("cours",String.valueOf(LevelList.get(position).getCours()));

                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Cours>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}