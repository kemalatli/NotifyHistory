plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 24
        targetSdk = 31
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
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    api(AndroidX.lifecycle.runtimeKtx)
    api(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)
    api(Libs.jodaTime)
    api(JakeWharton.timber)
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)
    api(Libs.autoStart)

}