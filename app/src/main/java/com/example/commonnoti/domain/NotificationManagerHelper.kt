package com.example.commonnoti.domain

import androidx.core.app.NotificationCompat

interface NotificationManagerHelper {
    fun showNotification(notification: NotificationCompat.Builder)
    fun cancelNotification(notificationId: Int)
}