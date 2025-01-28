package com.example.weather.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.weather.data.DataOrException
import com.example.weather.model.WeatherObject
import com.example.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {
    var data: MutableState<DataOrException<WeatherObject, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        loadWeather("Jalgaon")
    }
   private  fun loadWeather(city: String ) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.isDataLoading=true
            data.value=repository.getWeather(cityQuery = city)
            if (data.value.data.toString().isNotEmpty()) data.value.isDataLoading=false
        }
       Log.d("MainViewModel", "loadWeather: ${data.value.data}")
    }
}