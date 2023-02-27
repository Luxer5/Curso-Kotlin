package com.cursokotlin.weather.mainModule.model

import com.cursokotlin.weather.common.entities.WeatherForecastEntity

class MainRepository {
    private val remoteDatabase = RemoteDatabase()

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String,exclude: String, units: String,
                                      lang: String) : WeatherForecastEntity =
        remoteDatabase.getWeatherForecastByCoordiantes(lat, lon, appId,exclude, units, lang)
}