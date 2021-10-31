package com.example.thelynx

import android.app.Application
import com.example.thelynx.service.koinmodule.coreModule
import com.example.thelynx.service.koinmodule.repoModule
import com.example.thelynx.service.koinmodule.roomDatabaseModule
import com.example.thelynx.service.koinmodule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class LYNX : Application(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@LYNX)
            androidLogger()
            modules(listOf(coreModule, viewModelModule, repoModule, roomDatabaseModule))
        }
    }
}