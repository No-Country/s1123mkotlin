package com.nocountry.s1123mkotlin

import android.app.Application
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MediChildApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the Places API with your API key
        Places.initialize(applicationContext, "5b3ce3597851110001cf624891a26b2c3b3c4918a816569e37f4198b&amp;start=8.681495,49.41461&amp;end=8.687872,49.420318")
    }
}