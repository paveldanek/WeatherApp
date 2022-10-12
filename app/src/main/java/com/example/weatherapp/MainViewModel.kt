package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.CurrentCond

class MainViewModel(
    private val CurrentConditions: CurrentCond,
) : ViewModel() {

    private val _viewState = MutableLiveData<CurrentCond>()
    val viewState: LiveData<CurrentCond> = _viewState

    init {
        _viewState.value = CurrentConditions
    }
}