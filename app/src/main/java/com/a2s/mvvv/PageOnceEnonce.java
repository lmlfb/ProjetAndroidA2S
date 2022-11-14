package com.a2s.mvvv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
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
        TextView exo_question = findViewById(R.id.exo_question);


        TableLayout tl = (TableLayout) findViewById(R.id.table_exo);

        getEnonce(exo_titre, selectedExoLvl, exo_question);
        getTableContent(tl);

    }

    private void getEnonce(TextView exo_titre, int selectedExoLvl, TextView exo_question) {
        Call<List<Enonce>> call = RetrofitClient.getInstance().getMyApi().getSelectedLvlById(selectedExoLvl);
        call.enqueue(new Callback<List<Enonce>>() {
            @Override
            public void onResponse(Call<List<Enonce>> call, Response<List<Enonce>> response) {
                List<Enonce> Enoncelist = response.body();
                exo_titre.setText(Enoncelist.get(0).getTitre());
                exo_question.setText(Enoncelist.get(0).getQuestion());

  }
            @Override
            public void onFailure(Call<List<Enonce>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillTable(TableLayout tl, List<voiture> data) {


        TableRow tr_head = new TableRow(this);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //exo_titre.setText(String.valueOf(selectedExoLvl));
        // Toast.makeText(getApplicationContext(), String.valueOf(selectedExoLvl),Toast.LENGTH_LONG).show();


        Integer count=0;
        for(int i=0; i<data.size(); i++) {

            String modele = data.get(i).getmodele();// get the first variable
            int prix = data.get(i).getprix();// get the second variable

            TableRow tr = new TableRow(this);
            if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
            if(count%2==0) tr.setBackgroundColor(Color.BLUE);

            tr.setId(100+count);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            TextView DataModele = new TextView(this);
            DataModele.setId(200+count);
            DataModele.setText(modele);
            DataModele.setPadding(2, 0, 5, 0);
            DataModele.setTextColor(Color.RED);
            tr.addView(DataModele);

            TextView DataPrix = new TextView(this);
            DataPrix.setId(200+count);
            DataPrix.setText(prix);
            DataPrix.setPadding(2, 0, 5, 0);
            DataPrix.setTextColor(Color.RED);
            tr.addView(DataPrix);



// finally add this to the table row
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            count++;
        }

    }

    private void getTableContent(TableLayout tl) {
        Call<List<voiture>> call = RetrofitClient.getInstance().getMyApi().getCars();
        call.enqueue(new Callback<List<voiture>>() {
            @Override
            public void onResponse(Call<List<voiture>> call, Response<List<voiture>> response) {
                List<voiture> Enoncelist = response.body();

                fillTable(tl, Enoncelist);
            }
            @Override
            public void onFailure(Call<List<voiture>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}