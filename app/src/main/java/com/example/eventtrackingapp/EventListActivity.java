package com.example.eventtrackingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.util.Log;

public class EventListActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    LinearLayout eventContainer; // Container to hold event cards

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        dbHelper = new DatabaseHelper(this);
        eventContainer = findViewById(R.id.eventContainer); // Ensure this matches your XML

        loadEvents();
    }

    private void loadEvents() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_EVENTS, null);

        // Clear the container before adding new cards
        eventContainer.removeAllViews();

        while (cursor.moveToNext()) {
            int titleColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_TITLE);
            int descriptionColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_DESCRIPTION);
            int eventIdColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ID);
            int dateColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_DATE);
            int timeColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_TIME);

            if (titleColumnIndex == -1 || descriptionColumnIndex == -1 || eventIdColumnIndex == -1 || dateColumnIndex == -1 || timeColumnIndex == -1) {
                Toast.makeText(this, "Error: One or more columns are missing", Toast.LENGTH_SHORT).show();
                return; // Exit the method if columns are missing
            }

            String title = cursor.getString(titleColumnIndex);
            String description = cursor.getString(descriptionColumnIndex);
            String eventId = cursor.getString(eventIdColumnIndex);
            String date = cursor.getString(dateColumnIndex);
            String time = cursor.getString(timeColumnIndex);

            // Create the event card dynamically
            CardView cardView = new CardView(this);
            cardView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            cardView.setCardElevation(6);
            cardView.setRadius(12);

            // Create a LinearLayout to hold the event information
            LinearLayout cardContent = new LinearLayout(this);
            cardContent.setOrientation(LinearLayout.VERTICAL);
            cardContent.setPadding(16, 16, 16, 16);

            // Add title
            TextView titleTextView = new TextView(this);
            titleTextView.setText(title);
            titleTextView.setTextSize(18);
            titleTextView.setTextColor(getResources().getColor(android.R.color.black));
            cardContent.addView(titleTextView);

            // Add description
            TextView descriptionTextView = new TextView(this);
            descriptionTextView.setText(description);
            descriptionTextView.setTextSize(14);
            descriptionTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
            cardContent.addView(descriptionTextView);

            // Add date and time
            TextView dateTimeTextView = new TextView(this);
            dateTimeTextView.setText("Date: " + date + " | Time: " + time);
            dateTimeTextView.setTextSize(14);
            dateTimeTextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
            cardContent.addView(dateTimeTextView);

            // Add delete button
            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            deleteButton.setTextColor(getResources().getColor(android.R.color.white));
            deleteButton.setOnClickListener(v -> deleteEvent(eventId));
            cardContent.addView(deleteButton);

            // Set the content of the card view
            cardView.addView(cardContent);

            // Add the card to the container
            eventContainer.addView(cardView);
        }

        cursor.close();
    }

    private void deleteEvent(String eventId) {
        dbHelper.deleteEvent(eventId);

        // Reload events after deletion
        loadEvents();

        // Display a confirmation message
        Toast.makeText(this, "Event deleted successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadEvents(); // Refresh the event list when returning from AddEventActivity
        }
    }

    // Method for handling the FAB click to open AddEventActivity
    public void onAddEventClick(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivityForResult(intent, 1); // Use startActivityForResult to refresh on return
    }
}
