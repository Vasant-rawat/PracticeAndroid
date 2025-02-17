package com.example.weather.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.navigation.WeatherScreens
import com.example.weather.widgets.MainContent
import com.example.weather.widgets.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isDataLoading = true)
    ) {
        value = mainViewModel.getWeatherData(city.toString())
    }.value
    if (weatherData.isDataLoading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weatherData = weatherData.data, navController = navController)


    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(weatherData: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            title = weatherData.city.name + ",${weatherData.city.country}",
            navcontroller = navController, elevation = 5.dp
        )
    }) { padding ->
        MainContent(paddingValue = padding, data = weatherData)
    }
}


