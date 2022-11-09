package com.example.weatherapp.models

import com.squareup.moshi.Json

data class City(
    @Json(name = "timezone") val timeZone: Int,
)

data class FTemp(
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)

data class FWeatherData(
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val ftemp: FTemp,
    @Json(name = "weather") val weatherData: List<WeatherData>, //from CurrentConditions.kt (same package)
)

data class ForecastConditions(
    @Json(name = "cnt") val cnt: Int,
    @Json(name = "city") val city: City,
    @Json(name = "list") val fWeatherData: List<FWeatherData>,
)
