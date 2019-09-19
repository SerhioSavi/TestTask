package ru.savitskiy.testtask

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherProtocol {

    @GET("weather")
    abstract fun getWeather(@Query("APPID")  token: String,
                            @Query("lat")  lat: String,
                            @Query("lon")  lon: String
                            ) : Call<Weather>

}

data class Weather(
    val name: String,
    @SerializedName("main")
    val mainWeather: MainWeather
)

data class MainWeather(
    val temp: Double,
    val humidity: Int
)