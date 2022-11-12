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

public class PageExerciceList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo_list);
        ListView mylistView = findViewById(R.id.listViewLevel);

        Intent intent = getIntent();
        int selectedExoLvl = Integer.valueOf(intent.getStringExtra("selectedExoLvl"));
        Toast.makeText(getApplicationContext(), String.valueOf(selectedExoLvl),Toast.LENGTH_LONG).show();
        getEnonce(mylistView, selectedExoLvl);
    }
    private void getEnonce(ListView listView, int selectedExoLvl) {
        Call<List<Enonce>> call = RetrofitClient.getInstance().getMyApi().getSelectedLvl(selectedExoLvl);
        call.enqueue(new Callback<List<Enonce>>() {
            @Override
            public void onResponse(Call<List<Enonce>> call, Response<List<Enonce>> response) {
                List<Enonce> Enoncelist = response.body();
                String[] enonceStr = new String[Enoncelist.size()];
                for (int i = 0; i < Enoncelist.size(); i++) {
                    enonceStr[i] = String.valueOf(Enoncelist.get(i).getTitre());
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, enonceStr));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(getApplicationContext(), "position" + String.valueOf(id), Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(view.getContext(), PageOnceEnonce.class);
                        myIntent.putExtra("selected",String.valueOf(Enoncelist.get(position).getId()));
                        startActivity(myIntent);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Enonce>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}