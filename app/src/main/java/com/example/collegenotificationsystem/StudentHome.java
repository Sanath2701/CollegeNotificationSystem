package com.example.collegenotificationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegenotificationsystem.R;

public class StudentHome extends AppCompatActivity {
    TextView titleTextView;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        titleTextView = findViewById(R.id.title_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);

        // Retrieve data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");

            // Display data in TextViews
            titleTextView.setText(title);
            descriptionTextView.setText(description);
        }
    }
}
