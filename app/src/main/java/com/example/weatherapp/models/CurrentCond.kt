package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentCond(
    val cityName: String, val temp: ForecastTemp, val feelsLike: Float,
    val pressure: Float, val humidity: Int,
) : Parcelable
