package com.varol.boynews

import android.app.Application
import com.varol.boynews.di.*
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
                networkModule,
                useCaseModule,
                viewModelModule,
                repositoryModule
            )
        )
    }
}