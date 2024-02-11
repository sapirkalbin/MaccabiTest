package com.axppress.maccabitest

import android.app.Application
import com.axppress.maccabitest.di.localModule
import com.axppress.maccabitest.di.remoteModel
import com.axppress.maccabitest.di.usecases
import com.axppress.maccabitest.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(viewModels, usecases, remoteModel, localModule)
        }
    }
}