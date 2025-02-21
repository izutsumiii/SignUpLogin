package com.example.signupandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    TextView welcomeText;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcomeText = findViewById(R.id.helloText);
        logoutBtn = findViewById(R.id.logoutBtn);


//        String userName= getIntent().getStringExtra("username");
//        welcomeText.setText("Hello, " + userName + "!"); have to change the size of the layout first blah blah

        logoutBtn.setOnClickListener(view -> {
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
