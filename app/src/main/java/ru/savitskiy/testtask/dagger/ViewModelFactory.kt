package ru.savitskiy.testtask.dagger

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.MapKey
import dagger.Module
import ru.savitskiy.testtask.App
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

/**
 * Собственная фабрика по получению viewmodel с инжектированными зависимостями dagger'а
 */
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}
// viewmodel key
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

/**
 * функции расширения для удобного получения viewmodel в фрагменте
 */
fun <T : ViewModel> Fragment.injectViewModel(clazz: Class<T>) =
    ViewModelProviders.of(this, App.appComponent.getViewModelFactory())[clazz]

fun <T : ViewModel> AppCompatActivity.injectViewModel(clazz: Class<T>) =
    ViewModelProviders.of(this, App.appComponent.getViewModelFactory())[clazz]