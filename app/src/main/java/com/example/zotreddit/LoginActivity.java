package com.example.zotreddit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    private TextView etUsername;
    private Button btnSignIn;
    private TextView tvRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvRule = findViewById(R.id.tvRules);

        tvRule.setText("For each post in recycler view on the home page \n 1. Swipe left or right to delete \n " +
                "2. Tap on the post to upvote and see replies in another recycler view \n" + "3. Please enter your name");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                if (!username.isEmpty())
                {
                    intent.putExtra("user", username);
                    startActivity(intent);
                    etUsername.setText("");
                }
                else
                {
                    etUsername.setError("username cannot be empty");
                    etUsername.requestFocus();
                }


            }
        });

    }
}
