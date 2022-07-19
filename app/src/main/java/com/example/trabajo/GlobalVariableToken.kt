package com.example.trabajo

import android.app.Application

class GlobalTokenApplication:Application() {

    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }
}