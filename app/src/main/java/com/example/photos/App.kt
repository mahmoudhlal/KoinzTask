package com.example.photos

import android.app.Application
import com.example.photos.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, useCasesModule, networkModule, dbModule , mapperModule , repositoryModule))
        }
    }
}