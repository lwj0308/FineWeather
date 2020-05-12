package com.fineweather.android

import android.app.Application
import android.content.Context

/*
 * =====================================================================================
 * Summary: 
 * Author: LinLinLin
 * Create: 2020/5/11 23:45
 * =====================================================================================
 */class FineWeatherApplication : Application() {
    companion object {
        const val TOKEN = "JbapPD57oa5Qk1ne"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}