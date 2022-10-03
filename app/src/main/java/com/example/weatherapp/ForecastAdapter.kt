package com.example.weatherapp

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.provider.Settings.System.getString

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi

import androidx.core.content.res.TypedArrayUtils.getText

import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<ForecastViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val date: TextView
    private val sunrise: TextView
    private val sunset: TextView
    private val dayTemp: TextView
    private val minTemp: TextView
    private val maxTemp: TextView
//    private val pressure: TextView
//    private val humidity: TextView

    init {
        date = view.findViewById(R.id.forecast_date)
        sunrise = view.findViewById(R.id.forecast_sunrise)
        sunset = view.findViewById(R.id.forecast_sunset)
        dayTemp = view.findViewById(R.id.forecast_day_temp)
        minTemp = view.findViewById(R.id.forecast_min_temp)
        maxTemp = view.findViewById(R.id.forecast_max_temp)
//        pressure = view.findViewById(R.id.forecast_pressure)
//        humidity = view.findViewById(R.id.forecast_humidity)
    }

    private fun makeDate(stamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(stamp, 0, ZoneOffset.of("-5"))
        return formatter.format(dateTime)
    }

    private fun makeTime(stamp: Long): String {
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val dateTime = LocalDateTime.ofEpochSecond(stamp, 0, ZoneOffset.of("-5"))
        return timeFormatter.format(dateTime)
    }

    fun bind(item: DayForecast) {
        date.text = makeDate(item.date)
        sunrise.text = itemView.resources.getString(R.string.sunrise, makeTime(item.sunrise))
        sunset.text = itemView.resources.getString(R.string.sunset, makeTime(item.sunset))
        dayTemp.text = itemView.resources.getString(R.string.day_temp, item.temp.day.toInt().toString())
        minTemp.text = itemView.resources.getString(R.string.min_temp, item.temp.min.toInt().toString())
        maxTemp.text = itemView.resources.getString(R.string.max_temp, item.temp.max.toInt().toString())
//        pressure.text = item.pressure.toInt().toString()
//        humidity.text = item.humidity.toString()
    }
}