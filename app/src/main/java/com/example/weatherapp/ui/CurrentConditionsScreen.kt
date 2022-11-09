package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
//import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

import com.example.weatherapp.R
import com.example.weatherapp.models.CurrentConditions

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentConditions(
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    onForecastButtonClick: () -> Unit,
) {
    val state by viewModel.currentConditions.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.app_name)) }
    ) {
        state?.let {
            CurrentConditionsContent(it) {
                onForecastButtonClick()
            }
        }
    }
}

@Composable
private fun CurrentConditionsContent(
   currentConditions: CurrentConditions,
   onForecastButtonClick: () -> Unit,
) {
        Column(
            modifier = Modifier.padding(
                horizontal = 50.dp,
                vertical = 10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.city_name, currentConditions.cityName),
                style = TextStyle(
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.current_temp, currentConditions.conditions.temp.toInt()),
                        fontWeight = FontWeight(500),
                        fontSize = 70.sp
                    )
                    Text(stringResource(id = R.string.feels_like, currentConditions.conditions.feelsLike.toInt()))
                }
                Spacer(modifier = Modifier.weight(1f, true))
                AsyncImage(
                    model = String.format("https://openweathermap.org/img/wn/%s@2x.png", currentConditions.weatherData.firstOrNull()?.iconName),
                    modifier = Modifier.size(100.dp),
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
            ) {
                val textStyle = TextStyle(
                    fontSize = 16.sp,
                )
                Text(
                    stringResource(id = R.string.low_temp, currentConditions.conditions.minTemp.toInt()),
                    style = textStyle
                )
                Text(
                    stringResource(id = R.string.high_temp, currentConditions.conditions.maxTemp.toInt()),
                    style = textStyle
                )
                Text(
                    stringResource(id = R.string.humidity, currentConditions.conditions.humidity.toInt()),
                    style = textStyle
                )
                Text(
                    stringResource(id = R.string.pressure, currentConditions.conditions.pressure.toInt()),
                    style = textStyle
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            val onClick = {
                Log.d("Debugging", "onClickCall")
                onForecastButtonClick()
            }
            Button(onClick = onClick) {
                Text(
                    modifier = Modifier.width(90.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.forecast)
                )
            }
        }
}

@Preview
@Composable
fun CurrentConditionsPreview() {
    CurrentConditions {}
}


