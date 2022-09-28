package com.example.weatherapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentForecastBinding

private val forecastData = listOf(
    DayForecast(1664399820L, 1664366760L, 1664409480L,
        ForecastTemp(61f, 35f, 61f), 1000f, 43),
    DayForecast(1664486220L, 1664453220L, 1664495760L,
        ForecastTemp(55f, 42f, 68f), 1001f, 61),
    DayForecast(1664572620L, 1664539740L, 1664582040L,
        ForecastTemp(58f, 50f, 71f), 1002f, 62),
    DayForecast(1664659020L, 1664626200L, 1664668320L,
        ForecastTemp(60f, 51f, 71f), 1003f, 67),
    DayForecast(1664745420L, 1664712660L, 1664754600L,
        ForecastTemp(59f, 51f, 69f), 1004f, 72),
    DayForecast(1664831820L, 1664799120L, 1664840940L,
        ForecastTemp(61f, 50f, 72f), 1005f, 72),
    DayForecast(1664918220L, 1664885640L, 1664927220L,
        ForecastTemp(58f, 53f, 68f), 1006f, 73),
    DayForecast(1665004620L, 1664972100L, 1665013500L,
        ForecastTemp(61f, 55f, 69f), 1007f, 71),
    DayForecast(1665091020L, 1665058560L, 1665099780L,
        ForecastTemp(49f, 47f, 63f), 1008f, 76),
    DayForecast(1665177420L, 1665145020L, 1665186060L,
        ForecastTemp(50f, 42f, 62f), 1009f, 68),
    DayForecast(1665263820L, 1665231540L, 1665272340L,
        ForecastTemp(48f, 43f, 63f), 1010f, 74),
    DayForecast(1665350220L, 1665318000L, 1665358680L,
        ForecastTemp(60f, 45f, 64f), 1011f, 73),
    DayForecast(1665436620L, 1665404460L, 1665444960L,
        ForecastTemp(59f, 46f, 65f), 1012f, 72),
    DayForecast(1665523020L, 1665490920L, 1665531240L,
        ForecastTemp(51f, 47f, 65f), 1013f, 71),
    DayForecast(1665609420L, 1665577440L, 1665617520L,
        ForecastTemp(52f, 46f, 62f), 1014f, 70),
    DayForecast(1665695820L, 1665663900L, 1665703800L,
        ForecastTemp(54f, 45f, 67f), 1015f, 69),
    )

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    private lateinit var binding: FragmentForecastBinding
    private val args: ForecastFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.forecast)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastList.adapter = ForecastAdapter(forecastData)
    }
}