package ru.savitskiy.testtask.dagger

import androidx.annotation.NonNull
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.savitskiy.testtask.ApiWeatherProtocol
import javax.inject.Singleton

const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
const val tokenWeather = "ea1c3e0a7c2caea2d3d73da8dcf615f3"
@Module
class ServerApiModule {

    @Provides
    @NonNull
    @Singleton
    fun provideApiProtocol(): ApiWeatherProtocol {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(ApiWeatherProtocol::class.java)
    }
}