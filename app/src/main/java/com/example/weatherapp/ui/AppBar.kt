package com.example.weatherapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


@Composable
fun AppBar(title: String) {
    TopAppBar {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            style = TextStyle(
                fontWeight = FontWeight(500),
                fontSize = 17.sp
            ),
            text = title
        )
    } 
}

@Preview
@Composable
fun AppBarPreview() {
    AppBar(title = stringResource(id = R.string.app_name))
}