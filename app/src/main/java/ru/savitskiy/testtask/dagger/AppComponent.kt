package ru.savitskiy.testtask.dagger

import android.content.Context
import dagger.Component
import ru.savitskiy.testtask.ViewModelModule
import javax.inject.Singleton

@Component(modules = [ViewModelModule::class,
    ViewModelFactoryModule::class,
    ApplicationModule::class,
    ServerApiModule::class])

@Singleton
interface AppComponent {

    abstract fun getViewModelFactory(): ViewModelFactory
    abstract fun getContext(): Context
}