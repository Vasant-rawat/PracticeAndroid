package com.example.weather.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isDataLoading = true)
    ) {
        value = mainViewModel.getWeatherData("Jalgaon")
    }.value
    if(weatherData.isDataLoading==true){
        CircularProgressIndicator()
    }else if(weatherData.data !=null){
        MainScaffold(weatherData = weatherData.data, navController = navController)


    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(weatherData:Weather,navController: NavController) {
   Scaffold (topBar = {
       WeatherAppBar(
           title =weatherData.city.name +",${weatherData.city.country}",
           navcontroller = navController
           , elevation = 5.dp
       )
   }){
      MainContent(data=weatherData)
   }
}

@Composable
fun MainContent(data: Weather) {
    Text(text = data.city.name)
}
