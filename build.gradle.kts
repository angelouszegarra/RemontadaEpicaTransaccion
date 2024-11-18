// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false // Asegúrate de usar la versión más reciente
    id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
}

// Si necesitas agregar dependencias de classpath, usa buildscript:
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.4")
        classpath ("com.google.gms:google-services:4.4.2") // Necesario para Firebase
    }
}


