package br.com.bersoncrios.myandroidlab

import android.app.Application
import br.com.bersoncrios.myandroidlab.di.applicationModule
import br.com.bersoncrios.myandroidlab.di.courotinesModule
import br.com.bersoncrios.myandroidlab.di.networkModule
import br.com.bersoncrios.myandroidlab.features.starwars.di.startWarsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyAndroidLabApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@MyAndroidLabApplication)
            modules(
                listOf(
                    applicationModule,
                    networkModule,
                    courotinesModule,
                    startWarsModule
                )
            )
        }
    }
}