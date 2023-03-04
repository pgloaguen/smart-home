package me.pgloaguen.smarthome

import android.app.Application
import me.pgloaguen.smarthome.di.appModule
import me.pgloaguen.smarthome.di.dataModule
import me.pgloaguen.smarthome.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(dataModule, domainModule, appModule)
        }
    }
}