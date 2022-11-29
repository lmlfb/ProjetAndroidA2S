package com.a2s.mvvv;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                LinearLayout layout = findViewById(R.id.signInLayout);
                ProgressBar progressBar = new ProgressBar(PageLogin.this, null, android.R.attr.progressBarStyleLarge);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layout.addView(progressBar, params);
                progressBar.setVisibility(View.VISIBLE);

                getIsGoodLogin(login.getText().toString(), mdp.getText().toString(), progressBar);
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


    private void getIsGoodLogin(String login, String mdp, ProgressBar progressBar) {
        Call<List<Login>> call = RetrofitClient.getInstance().getMyApi().TryLogin(login, mdp);
        call.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<Login> Enoncelist = response.body();
                System.out.println(Enoncelist.get(0).isLogged);
                if(Enoncelist.get(0).isLogged == 1){

                    SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    System.out.println("\n--------------------------\n"+Enoncelist.get(0).getid_user()+"\n--------------------------\n");
                    myEdit.putInt("id", Enoncelist.get(0).getid_user());
                    myEdit.commit();

                    Toast.makeText(getApplicationContext(), "Welcome back "+login+" !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Bad password", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}