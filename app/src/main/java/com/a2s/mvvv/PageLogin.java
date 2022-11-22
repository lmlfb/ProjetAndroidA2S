package com.a2s.mvvv;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageLogin extends AppCompatActivity {
    private TextView forgotpassword;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView login = findViewById(R.id.newUserName);
        TextView mdp = findViewById(R.id.new_mdp);
        TextView loginOK = findViewById(R.id.loginOk);

        loginOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getIsGoodLogin(login.getText().toString(), mdp.getText().toString());
            }
        });
        TextView createAccount = findViewById(R.id.login_create_account);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), PageCreateAccount.class);
                startActivity(i);
            }
        });
        this.forgotpassword = (TextView) findViewById(R.id.forgotpassword);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpasswordactivity = new Intent(getApplicationContext(),Forgotpassword.class);
                startActivity(forgotpasswordactivity);
                finish();
            }
        });

    }
    private void getIsGoodLogin(String login, String mdp) {
        Call<List<Login>> call = RetrofitClient.getInstance().getMyApi().TryLogin(login, mdp);
        call.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                List<Login> Enoncelist = response.body();
                System.out.println(Enoncelist.get(0).isLogged);

                if(Enoncelist.get(0).isLogged == 1){
                    Toast.makeText(getApplicationContext(), "login good", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "bad login", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}