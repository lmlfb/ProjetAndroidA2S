package com.a2s.mvvv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Redirection vers la page principale "MainActivity" après 3 secondes.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Démarrage de la page
                Intent intent = new Intent(getApplicationContext(),PageLogin.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}