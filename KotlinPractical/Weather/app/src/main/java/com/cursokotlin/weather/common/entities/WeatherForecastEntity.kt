package com.cursokotlin.weather.common.entities

data class WeatherForecastEntity(
    val timezone: String, val current: Current, val hourly: List<Forecast>?)
