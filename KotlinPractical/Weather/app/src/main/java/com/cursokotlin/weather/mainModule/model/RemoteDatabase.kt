package com.cursokotlin.weather.mainModule.model

import com.cursokotlin.weather.common.dataAccess.WeatherService
import com.cursokotlin.weather.common.entities.WeatherForecastEntity
import com.cursokotlin.weather.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase {
    private var retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(WeatherService::class.java)

     suspend fun getWeatherForecastByCoordiantes(lat: Double, lon: Double, appId: String, exclude: String,
                                                 units: String, lang: String) : WeatherForecastEntity =
         withContext(Dispatchers.IO){
             service.getWeatherForecastByCoordinates(lat, lon, appId,exclude, units, lang)
     }
}