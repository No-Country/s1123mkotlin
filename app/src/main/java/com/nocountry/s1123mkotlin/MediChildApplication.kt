package com.nocountry.s1123mkotlin

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MediChildApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the Places API with your API key
        Places.initialize(applicationContext, "AIzaSyDsGewCqPjefeD5KAcVJ7ADdJQDlLN4E5A")
    }
}