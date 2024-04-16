package com.example.collegenotificationsystem;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CreateNotice extends AppCompatActivity {
    private static final String CHANNEL_ID = "MyNotificationChannel";

    EditText titleEditText;
    EditText descriptionEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);

        titleEditText = findViewById(R.id.title);
        descriptionEditText = findViewById(R.id.description);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                // Validate title and description
                if (!isValidTitle(title)) {
                    Toast.makeText(getApplicationContext(), "Invalid title format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidDescription(description)) {
                    Toast.makeText(getApplicationContext(), "Invalid description format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Proceed with submission logic
                // You can call a method to handle the submission process here

                // Pass data to StudentHomeActivity
                Intent intent = new Intent(CreateNotice.this, StudentHome.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                startActivity(intent);

                // Show a success message
                Toast.makeText(getApplicationContext(), "Notice created successfully!", Toast.LENGTH_SHORT).show();

                // Create and show the notification
                createAndShowNoticeNotification(title, description);
            }
        });
    }

    // Custom validation methods
    private boolean isValidTitle(String title) {
        // Define your validation rules for the title
        // For example, check if the title contains only letters and numbers
        return title.matches("[a-zA-Z0-9 ]+");
    }

    private boolean isValidDescription(String description) {
        // Define your validation rules for the description
        // For example, check if the description contains only letters and numbers
        return description.matches("[a-zA-Z0-9 ]+");
    }

    private void createAndShowNoticeNotification(String title, String description) {
        // Create the notification channel
        createNotificationChannel();

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "Channel for College Notification System";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
