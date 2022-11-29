package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.weatherapp.models.ForecastConditions
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import com.example.weatherapp.R
import com.example.weatherapp.models.FWeatherData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastConditions(
    viewModel: ForecastConditionsViewModel = hiltViewModel(),
) {
    val state by viewModel.forecastConditions.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.forecast)) }
    ) { state?.let {
            ForecastConditionsContent(it)
        }
    }
}

var t_zone: Int = 0

@Composable
private fun ForecastConditionsContent(
    forecastConditions: ForecastConditions,
)   {
        t_zone = forecastConditions.city.timeZone
        LazyColumn {
            items(items = forecastConditions.fWeatherData) { item: FWeatherData -> ForecastRow(item = item) }
        }
}

private fun makeDate(stamp: Long, zoneOffset: Int): String {
    val formatter = DateTimeFormatter.ofPattern("MMM d")
    val zone = (zoneOffset/3600).toString()
    val dateTime = LocalDateTime.ofEpochSecond(stamp, 0, ZoneOffset.of(zone))
    return formatter.format(dateTime)
}

private fun makeTime(stamp: Long, zoneOffset: Int): String {
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
    val zone = (zoneOffset/3600).toString()
    val dateTime = LocalDateTime.ofEpochSecond(stamp, 0, ZoneOffset.of(zone))
    return timeFormatter.format(dateTime)
}

@Composable
private fun ForecastRow(item: FWeatherData) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        AsyncImage(
            model = String.format("https://openweathermap.org/img/wn/%s@2x.png", item.weatherData.firstOrNull()?.iconName),
            modifier = Modifier.size(65.dp),
            contentDescription = "",
        )
        Column(
            modifier = Modifier.width(50.dp),
            horizontalAlignment = Alignment.End,
      ) {
            Text(makeDate(item.date, t_zone))
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Column {
            Text(text = stringResource(id = R.string.high_temp, item.ftemp.max.toInt()))
            Text(text = stringResource(id = R.string.low_temp, item.ftemp.min.toInt()))
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Column(
            horizontalAlignment = Alignment.End
      ) {
            Text(text = stringResource(id = R.string.sunrise, makeTime(item.sunrise, t_zone)))
            Text(text = stringResource(id = R.string.sunset, makeTime(item.sunset, t_zone)))
        }
        Spacer(modifier = Modifier.size(15.dp))
    }
}

@Preview
@Composable
private fun ForecastScreenPreview() {
    ForecastConditions()
}

