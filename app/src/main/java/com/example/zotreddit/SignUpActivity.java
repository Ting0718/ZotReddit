package com.example.zotreddit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";

    private TextView etPassword;
    private TextView etEmail;
    private Button btnSignUp;
    private TextView etLogin;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        etLogin = findViewById(R.id.etLogin);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();


                if (password.isEmpty() && email.isEmpty())
                {
                    etEmail.setError("Please enter an email");
                    etPassword.setError("Please enter a password");
                }

                else if (email.isEmpty())
                {
                    etEmail.setError("Please enter an email");
                    etEmail.requestFocus();
                }

                else if (password.isEmpty())
                {
                    etPassword.setError("Please enter a password");
                    etPassword.requestFocus();
                }

                else if (password.length() < 6)
                {
                    etPassword.setError("the password has to be least 6 characters");
                    etPassword.requestFocus();
                }

                else if (!(email.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                etEmail.setError(task.getException().getMessage());
                                Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                            }
                            else
                            {
                                Toast.makeText(SignUpActivity.this, "Signed up successfully, please login now", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }

                else {
                }
            }
        });

        etLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
