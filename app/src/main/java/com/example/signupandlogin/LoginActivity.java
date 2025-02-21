package com.example.signupandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginBtn;
    TextView signupRedirect;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.loginBtn);
        signupRedirect = findViewById(R.id.secReg);
        databaseHelper = new DatabaseHelper(this);

        loginBtn.setOnClickListener(view -> {
            String userName = username.getText().toString().trim();
            String userPass = password.getText().toString().trim();

            if (userName.isEmpty() || userPass.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Enter both username and password!", Toast.LENGTH_SHORT).show();
            } else if (databaseHelper.checkUserByUsername(userName, userPass)) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        signupRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
