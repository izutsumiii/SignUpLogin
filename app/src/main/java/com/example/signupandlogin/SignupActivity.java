package com.example.signupandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    EditText email, username, password, confirmPassword;
    Button signupBtn;
    TextView loginRedirect;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        email = findViewById(R.id.emailText);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        confirmPassword = findViewById(R.id.confPassword);
        signupBtn = findViewById(R.id.signupBtn);
        loginRedirect = findViewById(R.id.secLog);

        databaseHelper = new DatabaseHelper(this);


        signupBtn.setOnClickListener(view -> {
            String userEmail = email.getText().toString().trim();
            String userName = username.getText().toString().trim();
            String userPass = password.getText().toString().trim();
            String userConfirmPass = confirmPassword.getText().toString().trim();

            if (userEmail.isEmpty() || userName.isEmpty() || userPass.isEmpty() || userConfirmPass.isEmpty()) {
                Toast.makeText(SignupActivity.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            } else if (!userPass.equals(userConfirmPass)) {
                Toast.makeText(SignupActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            } else if (databaseHelper.checkUsername(userName)) {
                Toast.makeText(SignupActivity.this, "Username is already taken!", Toast.LENGTH_SHORT).show();
            } else {
                boolean insert = databaseHelper.insertData(userEmail, userName, userPass);
                if (insert) {
                    Toast.makeText(SignupActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignupActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        loginRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
