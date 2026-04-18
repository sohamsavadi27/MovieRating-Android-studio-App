# Movie Rating App (Android + Firebase)

## Overview

The Movie Rating App is a simple Android application that allows users to:

* Enter a movie name
* Give a rating using a rating bar
* Store data in Firebase Realtime Database
* View all saved movie ratings

This project demonstrates basic Android UI handling and Firebase integration.

---

## Features

* Add movie name
* Rate movies using RatingBar
* Store data in Firebase Realtime Database
* View all stored movies and ratings
* Simple and user-friendly interface

---

## Technologies Used

* Java (Android Development)
* Android Studio
* Firebase Realtime Database
* XML (UI Design)

---

## Project Structure

```
MovieRatingApp/
│
├── java/com.kamalclasses.movieratingapp/
│   └── MainActivity.java
│
├── res/layout/
│   └── activity_main.xml
│
├── AndroidManifest.xml
│
└── Gradle Scripts
```

---

## Setup Instructions

### 1. Clone the Repository

```
git clone <your-repo-link>
```

### 2. Open in Android Studio

* Open Android Studio
* Click on "Open Project"
* Select the project folder

### 3. Configure Firebase

1. Go to Firebase Console
2. Create a new project
3. Add an Android app
4. Download the `google-services.json` file
5. Place it inside the `app/` folder

### 4. Enable Realtime Database

* Go to Firebase Console → Realtime Database
* Create database
* Start in test mode

### 5. Add Internet Permission

Ensure this line exists in `AndroidManifest.xml`:

```
<uses-permission android:name="android.permission.INTERNET"/>
```

### 6. Run the App

* Connect your Android device or use an emulator
* Click Run in Android Studio

---

## How It Works

* User enters a movie name and selects a rating
* Clicking "Submit" saves data to Firebase
* Clicking "View" retrieves and displays all stored data

---

## Sample Data Format (Firebase)

```
movies
  ├── unique_id_1
  │     ├── mn: "Inception"
  │     └── rating: "4.5"
  ├── unique_id_2
        ├── mn: "Interstellar"
        └── rating: "5.0"
```

---

## Future Improvements

* Add delete/update functionality
* Improve UI design
* Add authentication
* Display data in RecyclerView

---

## Author

Soham Savadi
