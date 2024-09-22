package com.example.commonnoti

import android.app.Application
import com.example.commonnoti.di.notificationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(notificationModule)
        }
    }
}