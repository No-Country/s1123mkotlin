buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle-api:8.1.2")
        classpath("com.android.tools.build:gradle:8.1.2")

    }
}

plugins {
    id ("com.android.library") version "8.0.0" apply false
    kotlin("android") version "1.8.10" apply false
    id("com.android.application") version "7.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
