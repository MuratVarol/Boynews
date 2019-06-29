package com.varol.boynews

import android.app.Application
import com.varol.boynews.di.appModule
import com.varol.boynews.di.networkModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                appModule,
                networkModule
            )
        )
    }
}