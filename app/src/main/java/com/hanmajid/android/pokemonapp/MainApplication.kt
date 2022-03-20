package com.hanmajid.android.pokemonapp

import android.app.Application
import com.hanmajid.android.pokemonapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

/**
 * The app's main [Application] class.
 *
 * This class initialize the Koin dependency injection.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(AppModule().module)
        }
    }
}
