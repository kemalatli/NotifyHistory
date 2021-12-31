
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url ="https://jitpack.io")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:_")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    }
}