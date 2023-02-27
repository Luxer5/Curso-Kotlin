package com.cursokotlin.weather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.weather.R
import com.cursokotlin.weather.common.entities.WeatherForecastEntity
import com.cursokotlin.weather.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    private val result = MutableLiveData<WeatherForecastEntity>()
    fun getResult(): LiveData<WeatherForecastEntity> = result

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() =  snackbarMsg

    private val loaded = MutableLiveData<Boolean>()
    fun isLoaded() = loaded

    suspend fun getWeatherAndForecast (lat: Double, lon: Double, appId: String,exclude: String, units: String,
                                       lang: String){
        viewModelScope.launch {
            try{
                loaded.value = false
                val resultServer = repository.getWeatherAndForecast(lat, lon, appId,exclude, units, lang)
                result.value = resultServer
                if (resultServer.hourly == null || resultServer.hourly.isEmpty()){
                    snackbarMsg.value = R.string.main_error_empty_forecast
                }
            }catch (e: Exception){
                snackbarMsg.value = R.string.main_error_server
            }finally {
                loaded.value = true
            }
        }
    }
}