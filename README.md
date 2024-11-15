# 🍽️ Dish Display App - Quick Guide

## 📋 Overview
The **Dish Display App** helps users explore various dishes, search for recipes, and schedule cooking times. The app is designed to work seamlessly on both smartphones and tablets, with a focus on delivering an optimized experience for tablets in landscape mode.

> **Note**: This app was developed and tested using an Android virtual tablet device (9-inch emulator). The UI may require further optimization on physical tablets.

## 🛠️ Tools and Technologies Used
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Image Loading**: Coil
- **Navigation**: Jetpack Compose Navigation
- **Asynchronous Tasks**: Coroutines

## 🚀 How to Use the App

### 📍 Navigation
- After the **Splash Screen**, use the **navigation bar** to explore the app:
  - **Cook**: Browse and explore dishes.

### 🖼️ "What's on Your Mind?" Section
- This section displays categories like "Indian", "Desserts", etc., using images fetched from Google Images.
- Some data is hardcoded due to limited API availability.

### 🔍 Searching and Exploring Dishes
- Use the **search bar** at the top to quickly find dishes by name or category.
- Browse through various categories to explore dishes that match your cravings.

## 🎨 Changing the Background Color of a Dish Card
1. Tap on a dish and select **"Cook Now"**.
2. This action changes the background color of the selected dish card.
3. A rotating **timer icon** appears next to the search bar, indicating that the timer is running.

### ⏱️ Stopping the Timer
- To stop the timer, click the **"Delete" button** in the modal bottom sheet.
- This will reset the background color of the dish card and stop the rotating timer icon.

## 📱 User Interface
The app’s responsive UI adapts to all device sizes but is optimized for tablets in landscape mode to provide the best viewing experience.

### 📷 Screenshots

<img src="https://github.com/user-attachments/assets/b05d64da-9e0b-4b2a-9855-c7d4c7fab73f" alt="image1" width="180"/>
<img src="https://github.com/user-attachments/assets/c0b6cf8a-f3ed-4638-ade7-892adc38f5e6" alt="image2" width="180"/>

