package ru.savitskiy.testtask

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import ru.savitskiy.testtask.dagger.AppComponent
import ru.savitskiy.testtask.dagger.ApplicationModule

class App: Application() {
    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        appComponent = DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}