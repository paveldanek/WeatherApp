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

import com.example.weatherapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentConditions(
    onForecastButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.app_name)) }
  ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 50.dp,
                vertical = 10.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.city_name),
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
                        text = stringResource(id = R.string.current_temp, 72),
                        fontWeight = FontWeight(500),
                        fontSize = 70.sp
                    )
                    Text(stringResource(id = R.string.feels_like, 78))
                }
                Spacer(modifier = Modifier.weight(1f, true))
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.clear_sky),
                    contentDescription = "Clear_Sky"
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
                    stringResource(id = R.string.low_temp, 65),
                    style = textStyle
                )
                Text(
                    stringResource(id = R.string.high_temp, 80),
                    style = textStyle
                )
                Text(
                    stringResource(id = R.string.humidity, 100),
                    style = textStyle
                )
                Text(
                    stringResource(id = R.string.pressure, 1023),
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
}

@Preview
@Composable
fun CurrentConditionsPreview() {
    CurrentConditions {}
}


