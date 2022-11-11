package com.a2s.mvvv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_list);
        ListView mylistView = findViewById(R.id.listViewLevel);

        getLevels(mylistView);
    }



    private void getLevels(ListView listView) {
        Call<List<Level>> call = RetrofitClient.getInstance().getMyApi().getAllLevel();
        call.enqueue(new Callback<List<Level>>() {
            @Override
            public void onResponse(Call<List<Level>> call, Response<List<Level>> response) {
                List<Level> LevelList = response.body();
                String[] levelstr = new String[LevelList.size()];
                for (int i = 0; i < LevelList.size(); i++) {
                    levelstr[i] = LevelList.get(i).getTitre();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, levelstr));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getApplicationContext(), "position" + String.valueOf(id), Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(view.getContext(), PageExerciceList.class);
                        myIntent.putExtra("selectedExoLvl",String.valueOf(LevelList.get(position).getexoLvl()));
                        startActivity(myIntent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Level>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}