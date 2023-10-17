package com.nocountry.s1123mkotlin.model

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.auth.api.signin.GoogleSignIn.requestPermissions
import com.nocuntry.s1123mkotlin.R


class MedicationReminderWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Aquí puedes obtener información del trabajo, como el ID del recordatorio y el mensaje.
        val reminderId = inputData.getInt("reminderId", 0)
        val reminderMessage = inputData.getString("reminderMessage")

        // Verificar si el permiso `POST_NOTIFICATIONS` está disponible.
        val context = applicationContext
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Crear el canal de notificación.
            val channelId = "medication_channel"
            val channel = NotificationManagerCompat.from(context).getNotificationChannel(channelId)

            if (channel == null) {
                val channelName = "Recordatorio de Medicamento"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val newChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel(channelId, channelName, importance)
                } else {
                    null
                }

                newChannel?.let {
                    NotificationManagerCompat.from(context).createNotificationChannel(it)
                }
            }

            // Construir la notificación.
            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Recordatorio de Medicamento")
                .setContentText(reminderMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            // Mostrar la notificación.
            try {
                NotificationManagerCompat.from(context).notify(reminderId, builder.build())
            } catch (e: SecurityException) {
                // El usuario rechazó el permiso `POST_NOTIFICATIONS`.
                // Manejar el error aquí.
                return Result.failure()
            }

            return Result.success()
        } else {
            // Solicitar el permiso al usuario.
            // Aquí deberías implementar la lógica para solicitar el permiso al usuario de manera adecuada.
            return Result.failure()
        }
    }
}

