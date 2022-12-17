package com.example.weatherapp

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.models.LatitudeLongitude
import com.example.weatherapp.ui.CurrentConditions
import com.example.weatherapp.ui.ForecastConditions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var latitudeLongitude: LatitudeLongitude? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "CurrentConditions") {
                composable("CurrentConditions") {
                    val onResult = { value: Boolean ->
                        Log.d("TAG", "$value")
                        if (ActivityCompat.checkSelfPermission(
                                this@MainActivity,
                                ACCESS_COARSE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            fusedLocationProviderClient
                                .getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
                                .addOnSuccessListener { location ->
                                    latitudeLongitude = LatitudeLongitude(
                                        latitude = location.latitude.toFloat(),
                                        longitude = location.longitude.toFloat()
                                    )
                                }
                            navController.navigate("CurrentConditions")
                        }
                    }
                    val requestPermissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = onResult
                    )
                    CurrentConditions(
                        latitudeLongitude = latitudeLongitude,
                        onMyLocationButtonClick = {
                            requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
                        },
                        onForecastButtonClick = { navController.navigate("Forecast") }
                    )
                }

                composable("Forecast") {
                    ForecastConditions(
                        latitudeLongitude = latitudeLongitude
                    )
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
