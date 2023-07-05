package com.example.appstore

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@App)
        }
    }

    companion object {
        lateinit var instance: App private set
        lateinit var context: Context private set
    }
}
