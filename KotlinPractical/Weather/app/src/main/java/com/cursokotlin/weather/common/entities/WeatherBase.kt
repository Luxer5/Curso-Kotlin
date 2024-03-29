package com.cursokotlin.weather.common.entities

open class WeatherBase(
    dt: Long,
    humidity: Int,
    temp: Double,
    weather: List<Weather>)