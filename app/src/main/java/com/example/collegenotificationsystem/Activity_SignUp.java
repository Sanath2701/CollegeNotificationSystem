package com.example.collegenotificationsystem;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Activity_SignUp extends AppCompatActivity {
    private EditText empId, Email, Password, Name;
    private Button signIn_Button, signUp_Button;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        empId = findViewById(R.id.emp_id_signup);
        Email = findViewById(R.id.email_signup);
        Password = findViewById(R.id.password_signup);
        Name = findViewById(R.id.name);

        signIn_Button = findViewById(R.id.to_signin);
        signUp_Button = findViewById(R.id.signup);
        relativeLayout = findViewById(R.id.parent_SnackBar_signup);

        registerButtonsClickListener();
    }

    private void registerButtonsClickListener() {
        signIn_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signUp_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SIGN UP :", "SIGN UP TAPPED!!!");

                // Check for network connectivity
                Boolean isConnected = checkNetworkConnectivity();

                if (!isConnected) {
                    Log.d("SIGN UP :", "NOT CONNECTED!!");
                    Snackbar.make(relativeLayout, R.string.network_error, Snackbar.LENGTH_LONG).show();
                } else if (fieldsValid()) {
                    Log.d("SIGN UP :", "FIELDS ARE VALID!!");

                    // Validate email, name, and employee ID
                    if (!isEmailValid(Email.getText().toString())) {
                        Snackbar.make(relativeLayout, "Invalid Email Format!", Snackbar.LENGTH_SHORT).show();
                    } else if (!isNameValid(Name.getText().toString())) {
                        Snackbar.make(relativeLayout, "Invalid Name!", Snackbar.LENGTH_SHORT).show();
                    } else if (!isEmpIdValid(empId.getText().toString())) {
                        Snackbar.make(relativeLayout, "Invalid Employee ID!", Snackbar.LENGTH_SHORT).show();
                    } else {
                        // Proceed with sign-up logic
                        // Assuming sign-up is successful, navigate to the create button activity
                        Intent intent = new Intent(Activity_SignUp.this, CreateNotice.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    @NonNull
    private Boolean checkNetworkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private boolean fieldsValid() {
        if (TextUtils.isEmpty(empId.getText().toString()) ||
                TextUtils.isEmpty(Email.getText().toString()) ||
                TextUtils.isEmpty(Password.getText().toString()) ||
                TextUtils.isEmpty(Name.getText().toString())) {
            Snackbar.make(relativeLayout, R.string.invalid_fields, Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@gmail.com$");
    }

    private boolean isNameValid(String name) {
        return name.matches("^[a-zA-Z]+$");
    }

    private boolean isEmpIdValid(String empId) {
        return empId.matches("^[0-9]+$");
    }
}
