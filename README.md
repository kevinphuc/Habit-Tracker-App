

# Habit Tracker App

**Habit Tracker App** is a simple Android application that helps users build positive habits by tracking their daily progress. Users can create, monitor, and analyze habits, set reminders, and view streaks to stay motivated.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Habit Creation**: Easily add new habits with customizable names, icons, and schedules.
- **Daily Reminders**: Set daily notifications to remind users of their habits.
- **Progress Tracking**: View a habit completion history to track daily progress.
- **Habit Streaks**: Keep track of streaks (consecutive days of completing a habit).
- **Analytics and Visualization** (Future Feature): Basic graphs to display progress over time.
  
## Getting Started

This project is built with Android Studio and Firebase, and it's designed to help beginners learn Android development through hands-on experience.

### Prerequisites
- Android Studio (latest version)
- Firebase account
- Git for version control

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/kevinphuc/Habit-Tracker-App.git
   ```
2. **Open in Android Studio**:
   Open Android Studio, then choose "Open an existing project" and navigate to the cloned repository.

3. **Set up Firebase**:
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/).
   - Add the Firebase configuration file (`google-services.json`) to the `app` directory.
   - Enable Firestore and Authentication in Firebase.

4. **Run the project**:
   Build and run the app on an Android emulator or physical device.

## Technologies Used
- **Android Studio**: The main IDE used for Android development.
- **Kotlin**: Primary language for the app.
- **Firebase**:
  - **Firestore**: For storing and syncing habit data.
  - **Firebase Authentication**: To manage user authentication (optional feature).
  - **Firebase Analytics**: To track user engagement and app usage.
- **Jetpack Components**:
  - **ViewModel** and **LiveData** for data management.
  - **Navigation Component** for fragment navigation.
  - **Room** (optional) for offline data storage.

## Architecture
The app follows the **MVVM (Model-View-ViewModel)** architecture pattern:
- **Model**: Represents data and business logic.
- **ViewModel**: Exposes data streams to the UI, handling user interactions and data changes.
- **View**: Displays data and reacts to user interactions.

## Screenshots
> *Include images of the app screens here, such as home screen, habit creation, progress tracking, and analytics.*

## Contributing
Contributions are welcome! If you'd like to contribute, please fork the repository and submit a pull request with a detailed description of your changes.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


Copy and paste this into your `README.md` file. You can customize each section as needed, such as adding specific features, screenshots, or further setup details. Let me know if you'd like more help with any specific part!
