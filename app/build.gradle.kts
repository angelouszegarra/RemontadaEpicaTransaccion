plugins {
    alias(libs.plugins.android.application) // Asegúrate de definir correctamente esto en libs.versions.toml
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services") // Para integrar Firebase
    id ("com.google.devtools.ksp")// Para KSP (Room)
}

android {
    namespace = "com.example.remontadaepicatransaccion"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.remontadaepicatransaccion"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Usa la versión más reciente compatible
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Firebase BOM para manejar versiones automáticamente
    implementation(platform("com.google.firebase:firebase-bom:36.4.0")) // BOM de Firebase
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    // Dependencias de Room
    implementation("androidx.room:room-runtime:2.5.2") // Versión reciente de Room
    ksp("androidx.room:room-compiler:2.5.2") // Compilador de Room para KSP

    // Retrofit para llamadas API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Dependencias principales de Android
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Servicios de Google Play (para verificación por SMS y otros)
    implementation("com.google.android.gms:play-services-auth:20.6.0")
    implementation("com.google.android.gms:play-services-safetynet:18.0.1")

    // Escaneo de códigos QR (Zxing o MLKit)
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.mlkit:barcode-scanning:17.0.3")

    // Glide para carga de imágenes
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // Autenticación biométrica
    implementation("androidx.biometric:biometric:1.2.0-alpha05")

    // Jetpack Compose y Material Design
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material3:material3:1.2.0-alpha06")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")

    // LiveData y ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")
}
