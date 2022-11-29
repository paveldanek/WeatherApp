package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.CurrentConditions
import com.example.weatherapp.ui.ForecastConditions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private lateinit var viewModel: MainViewModel
//    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "CurrentConditions") {
                composable("CurrentConditions") {
                    CurrentConditions(
                        onForecastButtonClick = { navController.navigate("Forecast") }
                    )
                }
                composable("Forecast") {
                    ForecastConditions()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}