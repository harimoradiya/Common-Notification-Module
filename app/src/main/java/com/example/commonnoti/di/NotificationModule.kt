package com.example.commonnoti.di

import android.content.Context
import com.example.commonnoti.domain.NotificationManagerHelper
import com.example.commonnoti.presentation.AppNotificationManagerHelper
import org.koin.dsl.module


val notificationModule = module {

    single {
        get<Context>().getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
    }

    single<NotificationManagerHelper> {
        AppNotificationManagerHelper(get(), get())
    }
}