package com.example.collegenotificationsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_UserProfile extends AppCompatActivity {
    Button createNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        createNote = findViewById(R.id.create_note_button);
        registerButtonsClickListener(); // Register click listener for createNote button
    }

    private void registerButtonsClickListener() {
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_UserProfile.this, CreateNotice.class);
                startActivity(i);
            }
        });
    }
}
