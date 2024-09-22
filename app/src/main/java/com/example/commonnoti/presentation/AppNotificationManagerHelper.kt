package com.example.commonnoti.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.commonnoti.R
import com.example.commonnoti.domain.NotificationManagerHelper
import com.example.commonnoti.utils.NotificationUtils

class AppNotificationManagerHelper(private val context: Context, private val notificationManager: android.app.NotificationManager) : NotificationManagerHelper {
    override fun showNotification(notification: NotificationCompat.Builder) {
        // Create a notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationUtils.CHANNEL_ID,
                NotificationUtils.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Show the notification using the builder
        notificationManager.notify(NotificationUtils.NOTIFICATION_ID, notification.build())

    }


    override fun cancelNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }
}