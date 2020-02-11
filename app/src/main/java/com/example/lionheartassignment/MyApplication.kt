package com.example.lionheartassignment

import android.app.Application
import com.example.lionheartassignment.remote.VolleySingleton

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        VolleySingleton.initConfig(this)
    }
}