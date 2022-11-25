package com.a2s.mvvv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        EditText editTextResponse = findViewById(R.id.editTextResponse);
        ImageView one_exo_validation_state = findViewById(R.id.one_exo_validation_state);
        Button button_exo_confirm = findViewById(R.id.button_exo_confirm);
        getEnonce(exo_titre, selectedExoLvl, exo_question, button_exo_confirm, editTextResponse, one_exo_validation_state);

    }

    private void getEnonce(TextView exo_titre, int selectedExoLvl, TextView exo_question, Button button_exo_confirm, EditText editTextResponse,ImageView one_exo_validation_state) {
        Call<List<Enonce>> call = RetrofitClient.getInstance().getMyApi().getSelectedLvlById(selectedExoLvl);
        call.enqueue(new Callback<List<Enonce>>() {
            @Override
            public void onResponse(Call<List<Enonce>> call, Response<List<Enonce>> response) {
                List<Enonce> Enoncelist = response.body();
                exo_titre.setText(Enoncelist.get(0).getTitre());
                exo_question.setText(Enoncelist.get(0).getQuestion());
                //editTextResponse.setHint(Enoncelist.get(0).getReponse());

                button_exo_confirm.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String st1 = Enoncelist.get(0).getReponse();
                        String st2 = editTextResponse.getText().toString();
                        if(st1.equals(st2)){
                            one_exo_validation_state.setImageResource(R.drawable.validated);
                            System.out.println("##############");
                            //RetrofitClient.getInstance().getMyApi().validateExo(Enoncelist.get(0).getId(),Login.idStatic);
                            validateExercice();
                            Toast.makeText(getApplicationContext(), "ok done", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_SHORT).show();
                        }
                   }
                });


  }
            @Override
            public void onFailure(Call<List<Enonce>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateExercice() {
        Call<Void> call = RetrofitClient.getInstance().getMyApi().validateExo(3,19);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "INSERE", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}