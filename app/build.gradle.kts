plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.notifyhistory.android"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.0.5"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation (Google.android.material)
    implementation (AndroidX.core.ktx)
    implementation (AndroidX.compose.ui)
    implementation (AndroidX.compose.material)
    implementation (AndroidX.compose.ui.toolingPreview)
    implementation (AndroidX.activity.compose)
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    testImplementation (Testing.junit4)
    androidTestImplementation (AndroidX.test.ext.junit)
    androidTestImplementation (AndroidX.test.espresso.core)
    androidTestImplementation (AndroidX.compose.ui.testJunit4)
    debugImplementation (AndroidX.compose.ui.tooling)
}