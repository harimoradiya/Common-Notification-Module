package com.example.commonnoti.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.commonnoti.MainActivity
import com.example.commonnoti.NotificationIntentActivity
import com.example.commonnoti.R
import java.util.UUID

object NotificationUtils {

    const val CHANNEL_ID = "test_channel_id"
    const val CHANNEL_NAME = "Test Notifications"
    const val NOTIFICATION_ID = 101 // or use a utility to generate a unique ID

    fun generateUniqueNotificationId() :Int{
        return (System.currentTimeMillis() + UUID.randomUUID().mostSignificantBits).toInt()
    }

    fun createPendingIntent(context: Context, destinationActivity: Class<*>, requestCode: Int): PendingIntent {
        val intent = Intent(context, destinationActivity)
        return PendingIntent.getActivity(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE // Add FLAG_IMMUTABLE
        )
    }


    fun buildBigTextNotification(
        context: Context,
        title: String,
        message: String,
        channelId: String
    ): NotificationCompat.Builder {
        // Create an intent for an action button
        val actionIntent =   createPendingIntent(context,NotificationIntentActivity::class.java,0)

        // Build the notification
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, com.google.android.material.R.color.material_dynamic_neutral20))
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
            .setVibrate(longArrayOf(0, 200, 100, 200))
            .addAction(R.drawable.ic_notification, "Click me", actionIntent)
            .setGroup("group_key")
    }
}