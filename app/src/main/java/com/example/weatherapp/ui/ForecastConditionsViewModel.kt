package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.ForecastConditions
import com.example.weatherapp.service.OpenWeatherMapApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForecastConditionsViewModel @Inject constructor(private val api: OpenWeatherMapApi): ViewModel() {
    private val _forecastConditions = Channel<ForecastConditions>()

    public val forecastConditions: Flow<ForecastConditions> = _forecastConditions.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecastConditions = api.getForecastConditions()
        _forecastConditions.trySend(forecastConditions)
    }
}
