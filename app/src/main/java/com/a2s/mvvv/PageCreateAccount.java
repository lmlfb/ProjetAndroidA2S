package com.a2s.mvvv;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

public class PageCreateAccount extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        TextView login = findViewById(R.id.newUserName);
        TextView mdp = findViewById(R.id.new_mdp);

        Button createAccount = findViewById(R.id.new_account);
        TextView backToLogin = findViewById(R.id.go_to_login);


// To dismiss the dialog
        //progress.dismiss();

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

                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                LinearLayout layout = findViewById(R.id.linearLayoutNewAccount);
                ProgressBar progressBar = new ProgressBar(PageCreateAccount.this, null, android.R.attr.progressBarStyleLarge);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 150);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layout.addView(progressBar, params);
                progressBar.setVisibility(View.VISIBLE);

                createUser(login.getText().toString(), mdp.getText().toString(), login, progressBar);
            }
        });

}
    private void createUser(String login, String mdp, TextView userNameField, ProgressBar progressBar) {
        Call<List<UserCreated>> call = RetrofitClient.getInstance().getMyApi().AddUser(login,mdp);
        call.enqueue(new Callback<List<UserCreated>>() {
            @Override
            public void onResponse(Call<List<UserCreated>> call, Response<List<UserCreated>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<UserCreated> res = response.body();
                System.out.println("///////////////////////////////////////");
                System.out.println(res.get(0).IsUserCreated);
                System.out.println("///////////////////////////////////////");

                int Done = res.get(0).IsUserCreated;

                if(Done == 1){
                    Toast.makeText(getApplicationContext(), "Account had been created, now sign in", Toast.LENGTH_SHORT).show();
                    mHandler.postDelayed(new Runnable() {
                        public void run() {
                            goToSignIn();
                        }
                    }, 2000);
                }
                if(Done == 0){
                    Toast.makeText(getApplicationContext(), "Login already used, change login", Toast.LENGTH_SHORT).show();

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


                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToSignIn() {
        Intent intent = new Intent(PageCreateAccount.this, PageLogin.class);

        startActivity(intent);
    }
}