plugins {
    id("dagger.hilt.android.plugin")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id ("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")

}


android {
    namespace = ("com.nocuntry.s1123mkotlin")
    compileSdk = (34)

    defaultConfig {
        applicationId = "com.nocountry.s1123mkotlin"
        minSdkVersion(26)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packagingOptions {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
    kapt {
        javacOptions {
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }
}

dependencies {
    //ViewModel:
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata:2.3.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    //openIA
    implementation ("com.aallam.openai:openai-client")
    implementation("androidx.benchmark:benchmark-macro:1.2.0")
    runtimeOnly ("io.ktor:ktor-client-okhttp")

    implementation ("com.aallam.openai:openai-client-bom:3.5.0")
    implementation ("com.aallam.openai:openai-client:3.3.0")

    implementation ("io.ktor:ktor-client-android:2.3.2")
    implementation ("io.ktor:ktor-client-okhttp:2.3.2")

    implementation ("com.aallam.openai:openai-client")
    implementation ("io.ktor:ktor-client-okhttp")

    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation ("io.coil-kt:coil-compose:1.3.2")

    // Dependencias de Compose Material 3
    implementation("androidx.compose.material3:material3:1.0.0-beta01")
    implementation("androidx.compose.material3:material3")

    //Alarm
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.work:work-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")
    implementation("androidx.work:work-runtime-ktx:2.7.1")

    implementation("androidx.core:core-ktx:1.8.0")

    implementation("androidx.constraintlayout:constraintlayout-compose-android:1.1.0-alpha13")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-sessions:1.0.2")
    implementation("com.google.firebase:protolite-well-known-types:18.0.0")
    implementation("com.google.android.engage:engage-core:1.3.0")
    implementation("com.google.firebase:firebase-inappmessaging-ktx:20.3.5")
    implementation("com.google.android.gms:play-services-mlkit-text-recognition-common:19.0.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")

    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Dependencia de Room
    val room_version = "2.5.0"
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-runtime:2.4.0")
    implementation("androidx.room:room-ktx:2.4.0")
    implementation("androidx.room:room-runtime:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0-alpha02")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha02")
    implementation("androidx.room:room-ktx:2.5.2")

    //DateTimerPicker
    implementation("com.google.android.material:material:1.5.0")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    // Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation("androidx.compose.ui:ui:1.0.4")
    implementation("androidx.compose.ui:ui-tooling:1.0.4")
    implementation("androidx.compose.material:material:1.0.4")
    implementation("androidx.activity:activity-compose:1.3.1")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Firebase
    implementation("com.google.firebase:firebase-analytics:20.0.0") // Asegúrate de tener la versión correcta
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.activity:activity-ktx:1.3.1")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}
kapt {
    correctErrorTypes = true
}
hilt {
    enableAggregatingTask = true
}