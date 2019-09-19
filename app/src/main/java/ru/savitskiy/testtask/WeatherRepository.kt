package ru.savitskiy.testtask

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.savitskiy.testtask.dagger.tokenWeather
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiWeatherProtocol: ApiWeatherProtocol) {

    fun getWeather(lat: String, lon: String, onSuccess: (Weather?) -> Unit, onError: (String?) -> Unit) {
        apiWeatherProtocol.getWeather(tokenWeather, lat, lon).enqueue(object : Callback<Weather> {
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                onError.invoke(t.message)
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                onSuccess.invoke(response.body())
            }
        })
    }
}