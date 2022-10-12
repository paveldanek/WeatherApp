package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayForecast(
    val date: Long, val sunrise: Long, val sunset: Long,
    val temp: ForecastTemp, val pressure: Float, val humidity: Int,
) : Parcelable
