package com.example.budgetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email, password;
    private Button registerbtn;
    private TextView registerqn;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerbtn = findViewById(R.id.registerbtn);
        registerqn = findViewById(R.id.registerqn);

        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // Initialize progressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registration in Progress");
        progressDialog.setCanceledOnTouchOutside(false);

        registerqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, com.example.budgetapp.loginActivity.class);
                startActivity(intent);
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if (TextUtils.isEmpty(emailString)) {
                    email.setError("Email is Required !!");
                } else if (TextUtils.isEmpty(passwordString)) {
                    // Change this to set an error for the password field
                    password.setError("Password is Required !!");
                } else {
                    progressDialog.show(); // Show the progressDialog

                    mAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(RegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }

                            // Dismiss the progressDialog in both success and failure cases
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
    }
}
