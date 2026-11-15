# Android Task App

A native Android task-list project built with Kotlin and AndroidX. Tasks can be created with an optional deadline and description, searched, and retained across configuration changes. Tasks are held in memory and do not persist across app restarts.

## Build and run

Open the project in Android Studio, sync Gradle, and run the `app` configuration on an emulator or device.

- Minimum Android version: API 24
- Compile/target SDK: API 36
- Gradle runtime: JDK 17 or newer (the app bytecode targets Java 11)

```bash
./gradlew test
./gradlew connectedAndroidTest
```

## Project structure

```text
app/src/main/java/com/example/todo/MainActivity.kt   Main task screen
app/src/main/java/com/example/todo/NewTask.kt        Task-entry screen
app/src/main/res/layout/                             Screen layouts
app/src/test/                                        JVM unit tests
app/src/androidTest/                                 Android instrumentation tests
```


