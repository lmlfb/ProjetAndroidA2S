package com.a2s.mvvv;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageCreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        TextView login = findViewById(R.id.newUserName);
        TextView mdp = findViewById(R.id.new_mdp);

        Button createAccount = findViewById(R.id.new_account);
        Button backToLogin = findViewById(R.id.go_to_login);

        backToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), PageLogin.class);
                startActivity(i);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                createUser(login.getText().toString(), mdp.getText().toString(), login);
            }
        });

}
    private void createUser(String login, String mdp, TextView userNameField) {
        Call<List<UserCreated>> call = RetrofitClient.getInstance().getMyApi().AddUser(login,mdp);
        call.enqueue(new Callback<List<UserCreated>>() {
            @Override
            public void onResponse(Call<List<UserCreated>> call, Response<List<UserCreated>> response) {
                List<UserCreated> res = response.body();
                System.out.println("///////////////////////////////////////");
                System.out.println(res.get(0).IsUserCreated);
                System.out.println("///////////////////////////////////////");

                int Done = res.get(0).IsUserCreated;

                if(Done == 1){
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();

                }
                if(Done == 0){
                    Toast.makeText(getApplicationContext(), "NOT DONE", Toast.LENGTH_SHORT).show();

                }

                /*if(res.get(0).getIsUserCreated() == 1){

                    //Intent i = new Intent(getApplicationContext(), PageLogin.class);
                    //startActivity(i);
                    //Toast.makeText(getApplicationContext(), "got it", Toast.LENGTH_SHORT).show();
                }
                if(res.get(0).getIsUserCreated() == 0){
                    //Toast.makeText(getApplicationContext(), "already existing login", Toast.LENGTH_SHORT).show();

                }
                else{
                    //Toast.makeText(getApplicationContext(), res.get(0).getIsUserCreated(), Toast.LENGTH_SHORT).show();

                }*/
            }
            @Override
            public void onFailure(Call<List<UserCreated>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}