plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral() // Replace jcenter() with mavenCentral()
    }
    dependencies {
        // Firebase Gradle Plugin (optional, if needed for Firebase integration)
        classpath("com.google.gms:google-services:4.3.3") // Updated version
    }
}
