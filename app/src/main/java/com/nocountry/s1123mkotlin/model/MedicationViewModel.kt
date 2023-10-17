package com.nocountry.s1123mkotlin.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MedicationViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)

    fun scheduleMedicationReminder(reminderId: Int, reminderMessage: String, delayMillis: Long) {
        val inputData = Data.Builder()
            .putInt("reminderId", reminderId)
            .putString("reminderMessage", reminderMessage)
            .build()

        val notificationWork = OneTimeWorkRequest.Builder(MedicationReminderWorker::class.java)
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
            .setInputData(inputData)
            .build()

        workManager.enqueue(notificationWork)
    }
}


