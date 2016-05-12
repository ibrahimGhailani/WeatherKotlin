package space.ibrahim.weatherkotlin.common

import android.app.Application
import space.ibrahim.weatherkotlin.common.injection.AppComponent
import space.ibrahim.weatherkotlin.common.injection.DaggerAppComponent
import space.ibrahim.weatherkotlin.common.injection.NetworkModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .build()

        appComponent.inject(this)
    }
}