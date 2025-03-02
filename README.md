# Event Tracking App

## Project Overview
This repository contains the **Event Tracking App**, a mobile application designed to help users efficiently manage and track upcoming events. The app provides an intuitive user interface for adding, updating, and deleting events while also sending **SMS notifications** for scheduled events. The application follows **Android Design Guidelines** to ensure a smooth and user-friendly experience.

## User Needs and App Goals
The app is designed to address the following user needs:
- **Event Organization:** Users can add, edit, and delete events seamlessly.
- **Reminders & Notifications:** SMS notifications remind users about upcoming events.
- **Secure Login System:** Ensures authorized access to event data.
- **Simple & Intuitive UI:** A clean interface for easy event management.

## UI Design & Features
The app includes the following screens and features to support a **user-centered design**:

### **1. Login Screen (`LoginActivity.java` & `activity_login.xml`)**
   - Allows users to securely log in.
   - Provides an option to create an account if not registered.

### **2. Event List Screen (`EventListActivity.java` & `activity_event_list.xml`)**
   - Displays events in a list format.
   - Users can **add** or **delete** events easily.
   - Ensures a **visually hierarchical design** for easy navigation.

### **3. Add Event Screen (`AddEventActivity.java` & `activity_add_event.xml`)**
   - Users can enter event details (title, date, time, and description).
   - Saves event details to the **SQLite database**.

### **4. Database Management (`DatabaseHelper.java`)**
   - Manages event storage using **SQLite** for persistent data.
   - Handles CRUD operations (Create, Read, Update, Delete).

### **5. SMS Notification Screen (`SmsPermissionActivity.java` & `activity_sms_permission.xml`)**
   - Requests SMS permission to send reminders.
   - Notifies users about upcoming events.

### **User-Centered Design Approach**
The UI was designed with **usability and accessibility** in mind:
- **Clear visual hierarchy** ensures easy navigation.
- **Consistent UI elements** across all screens.
- **Minimalist approach** reduces complexity.
- **Permission handling** ensures smooth SMS notifications.

## Coding Approach & Strategies
The app was built using the following techniques:
- **Android Studio Layout Editor** for UI design.
- **Java with SQLite** for database operations.
- **Intent-based navigation** for smooth screen transitions.
- **MVC (Model-View-Controller) architecture** for code organization.
- **Error handling & validation** for robust performance.

### **Future Applications of These Strategies**
- Can be extended to incorporate **Firebase authentication**.
- Can support **cloud-based event storage** for multi-device access.
- The **modular approach** allows easy feature expansion.

## Testing & Debugging Process
To ensure functionality and reliability, the app was tested using:
- **Unit testing** for database operations (`DatabaseHelper.java`).
- **UI testing** for button clicks and event interactions.
- **Permissions testing** for SMS notifications.
- **Manual testing** on Android Emulator and physical devices.

### **Importance of Testing**
- Prevented **data loss** by verifying CRUD operations.
- Ensured **SMS notifications** worked as expected.
- Identified **UI layout issues** across different screen sizes.
- Improved overall **app stability**.

## Challenges & Innovations
Throughout development, several challenges were encountered:
- **Handling Android SMS permissions:**  
  - Solution: Used `ActivityCompat.requestPermissions()` to ensure proper runtime permission handling.
- **Ensuring smooth UI navigation:**  
  - Solution: Implemented explicit **Intents** to switch between activities.
- **Managing persistent event data efficiently:**  
  - Solution: Optimized **SQLite queries** to improve database performance.

## Key Success Areas
One of the most **notable successes** in this project was implementing the **SQLite database with seamless UI integration**. The database efficiently stores and retrieves events, ensuring that users can manage their schedules effectively. The **SMS notification system** was another major achievement, allowing timely event reminders.

## Repository Contents
### **Java Files**
- **`AddEventActivity.java`** – Handles event creation.
- **`DatabaseHelper.java`** – Manages SQLite database operations.
- **`EventListActivity.java`** – Displays a list of events.
- **`LoginActivity.java`** – Manages user authentication.
- **`SmsPermissionActivity.java`** – Handles SMS notification permissions.

### **XML Layout Files**
- **`activity_add_event.xml`** – Layout for adding an event.
- **`activity_event_list.xml`** – Layout for displaying the event list.
- **`activity_login.xml`** – Layout for the login screen.
- **`activity_sms_permission.xml`** – Layout for requesting SMS permissions.

## How to Run the App
1. Clone this repository:
   ```sh
   git clone https://github.com/your-username/event-tracking-app.git