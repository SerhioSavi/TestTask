package ru.savitskiy.testtask

import dagger.Component
import javax.inject.Singleton

@Component(modules = [ViewModelModule::class, ViewModelFactoryModule::class, ApplicationModule::class])

@Singleton
interface AppComponent {

    abstract fun getViewModelFactory(): ViewModelFactory
}