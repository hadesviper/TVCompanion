package com.prtd.serial.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainAppDI:Application() {
    override fun onCreate() {
        super.onCreate()
       /* NoKill().init(object :CrashHandler{
            override fun uncaughtException(t: Thread, e: Throwable) {
                Log.e("Crash Error", "uncaughtException: ${e.message}", )
            }
        })*/
    }
}