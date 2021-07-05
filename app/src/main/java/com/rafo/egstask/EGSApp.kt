package com.rafo.egstask

import android.app.Application
import com.rafo.data.di.apiModule
import com.rafo.data.di.repositoryModule
import com.rafo.domain.di.interactorsModule
import com.rafo.egstask.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EGSApp : Application() {

    private val modules = listOf(
        apiModule, repositoryModule, interactorsModule, viewModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@EGSApp)
            modules(modules)
        }
    }
}