package com.cursokotlin.weather.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlin.weather.BR
import com.cursokotlin.weather.R
import com.cursokotlin.weather.common.entities.Forecast
import com.cursokotlin.weather.common.utils.CommonUtils
import com.cursokotlin.weather.databinding.ActivityMainBinding
import com.cursokotlin.weather.mainModule.view.adapters.ForecastAdapter
import com.cursokotlin.weather.mainModule.view.adapters.OnClickListener
import com.cursokotlin.weather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() , OnClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupviewModel()
        setupObservers()
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupviewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackbarMsg().observe(this){ resMsg ->
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
            it.getResult().observe(this){ result ->
                adapter.submitList(result.hourly)
            }
        }
    }

    private fun setupAdapter(){
        adapter = ForecastAdapter(this)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            binding.viewModel?.getWeatherAndForecast(41.6522, -4.7304,
                "6a5c325c9265883997730d09be2328e8","hourly" , "metric", "en")
        }
    }

    /*
    * OnClickListener
    **/
    override fun onClick(forecast: Forecast) {
        Snackbar.make(binding.root, CommonUtils.getFullDate(forecast.dt), Snackbar.LENGTH_LONG).show()
    }


}