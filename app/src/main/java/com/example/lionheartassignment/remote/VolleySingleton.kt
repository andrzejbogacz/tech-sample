package com.example.lionheartassignment.remote

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VolleySingleton {

    private lateinit var context: Context

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    fun initConfig(context: Context) {
        this.context = context.applicationContext
    }

}