package com.cursokotlin.weather.mainModule.view.adapters

import com.cursokotlin.weather.common.entities.Forecast

interface OnClickListener {
    fun onClick(forecast: Forecast)
}