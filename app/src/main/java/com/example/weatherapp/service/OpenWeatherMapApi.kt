package com.example.weatherapp.service

import com.example.weatherapp.models.CurrentConditions
import com.example.weatherapp.models.ForecastConditions
import retrofit2.http.GET
import retrofit2.http.Query

const val ZIP = "55130" // Weather at the ZIP Code determined by this constant

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String = ZIP,
        @Query("appid") apiKey: String = "c9d24f575f811a6c2e2211eebba60e67",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/weather")
    suspend fun getMyLocationCurrentConditions(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String = "c9d24f575f811a6c2e2211eebba60e67",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecastConditions(
        @Query("zip") zip: String = ZIP,
        @Query("appid") apiKey: String = "c9d24f575f811a6c2e2211eebba60e67",
        @Query("cnt") cnt: Int = 16,  // 16-day forecast
        @Query("units") units: String = "imperial"
    ) : ForecastConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getMyLocationForecastConditions(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String = "c9d24f575f811a6c2e2211eebba60e67",
        @Query("cnt") cnt: Int = 16,  // 16-day forecast
        @Query("units") units: String = "imperial"
    ) : ForecastConditions
}