package com.a2s.mvvv;



import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayoutLevelList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo_list);
        ListView mylistView = findViewById(R.id.listViewLevel);
        getEnonce(mylistView);
        Toast.makeText(getApplicationContext(), "We are moved to second Activity",Toast.LENGTH_LONG).show();
    }

    private void getEnonce(ListView listView) {
        Call<List<Enonce>> call = RetrofitClient.getInstance().getMyApi().getAllEnonce();
        call.enqueue(new Callback<List<Enonce>>() {
            @Override
            public void onResponse(Call<List<Enonce>> call, Response<List<Enonce>> response) {
                List<Enonce> Enoncelist = response.body();
                String[] enonceStr = new String[Enoncelist.size()];
                for (int i = 0; i < Enoncelist.size(); i++) {
                    enonceStr[i] = Enoncelist.get(i).getTitre();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, enonceStr));
            }

            @Override
            public void onFailure(Call<List<Enonce>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });
    }
}