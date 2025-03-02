package com.example.eventtrackingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity {

    EditText etEventTitle, etEventDescription, etEventDate, etEventTime;
    Button btnSaveEvent, btnDeleteEvent;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event); // This corresponds to your layout

        // Initialize the views
        etEventTitle = findViewById(R.id.etEventTitle);
        etEventDescription = findViewById(R.id.etEventDescription);
        etEventDate = findViewById(R.id.etEventDate);
        etEventTime = findViewById(R.id.etEventTime);
        btnSaveEvent = findViewById(R.id.btnSaveEvent);
        btnDeleteEvent = findViewById(R.id.btnDeleteEvent);

        // Initialize the database helper
        dbHelper = new DatabaseHelper(this);

        // Save event when the save button is clicked
        btnSaveEvent.setOnClickListener(v -> {
            String title = etEventTitle.getText().toString();
            String description = etEventDescription.getText().toString();
            String date = etEventDate.getText().toString();
            String time = etEventTime.getText().toString();

            if (title.isEmpty() || description.isEmpty() || date.isEmpty() || time.isEmpty()) {
                // Show a message if any field is empty
                Toast.makeText(AddEventActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save the event to the database
                saveEventToDatabase(title, description, date, time);
                Toast.makeText(AddEventActivity.this, "Event Saved!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Set result to OK to indicate success
                finish(); // Close the activity and return to previous screen
            }
        });

        // Delete event when the delete button is clicked (you can implement this functionality as needed)
        btnDeleteEvent.setOnClickListener(v -> {
            // Here, you can implement the delete functionality if needed
            finish(); // Close the activity (you could add a delete event method if needed)
        });
    }

    // Method to save the event to the database
    private void saveEventToDatabase(String title, String description, String date, String time) {
        // Insert the event into the database using the DatabaseHelper
        dbHelper.insertEvent(title, description, date, time);
    }
}
