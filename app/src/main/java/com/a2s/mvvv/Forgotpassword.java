package com.a2s.mvvv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Forgotpassword extends AppCompatActivity {

    private TextView loginpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        this.loginpage = (TextView) findViewById(R.id.loginpage);

        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnloginpage = new Intent(getApplicationContext(), PageLogin.class);
                startActivity(returnloginpage);
                finish();
            }
        });

    }
}