# 🏠 Android Food UI Demo

This project showcases a modern Android application with clean architecture and Compose UI.  
It features a **Home tab** with banners and categories, and an **Orders tab** displaying ongoing or past orders.

---

## ✨ Features

- **Home Screen**: Displays promotional banners and product categories.
- **Orders Screen**: Shows user's past and ongoing orders.
- **Modern UI**: Built using **Jetpack Compose** and **Material3**.
- **Shared ViewModel** across tabs using **StateFlow** for consistent state management.
- **Dummy data** fetched via JSON from Mocki simulating real-world API calls.
- **Clean, modular and scalable codebase** ready for production use.

---

## 🛠️ Approach & Architecture

This project follows **MVVM Clean Architecture** with a clear separation of concerns:

### 🧱 Layers
- **UI Layer**: Jetpack Compose + Material3 for modern, declarative UI.
- **Domain Layer**: Contains business logic in the form of use-cases.
- **Data Layer**: Provides dummy data via Mocki JSON endpoint.

### 🔧 Tech Stack
- **Jetpack Compose**
- **Material3**
- **Kotlin Coroutines & Flow**
- **Hilt** for Dependency Injection
- **StateFlow** for UI state handling
- **Retrofit** for network communication
- **Sealed classes** for managing UI states
- **Extension functions** for code clarity and reuse

---

## ✅ Highlights

- Shared `ViewModel` for consistent state across navigation tabs.
- Proper **loading, success, and error UI states** using sealed classes.
- Architecture supports easy integration of real APIs.
- Follows **OOP** and modern **Android development best practices**.
- UI consistency is maintained across scrolls and tab switches.
- Designed to be **test-ready**, even though unit tests were skipped due to time constraints.

---

## 📦 Project Structure
├── ui/ # Jetpack Compose screens, viewmodel & components

├── domain/ # UseCases and models

├── data/ # Repository and Mock API source

├── di/ # Hilt modules for DI

├── utils/ # Extensions and constants


---

## 🚀 Getting Started

1. Clone the repo: git clone https://github.com/Boradeg/FoodDeliveryApp_Task.git
2. Open in Android Studio.
3. Run the app on an emulator or device.

