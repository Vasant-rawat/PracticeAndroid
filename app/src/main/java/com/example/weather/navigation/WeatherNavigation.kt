package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather.screens.about.AboutScreen
import com.example.weather.screens.favorites.FavoriteScreen
import com.example.weather.screens.main.MainScreen
import com.example.weather.screens.main.MainViewModel
import com.example.weather.screens.search.SearchScreen
import com.example.weather.screens.settings.SettingScreen
import com.example.weather.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(route = WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        val route=WeatherScreens.MainScreen.name
        composable(route = "$route/{city}",
            arguments = listOf(
                navArgument(name="city"){
                    type= NavType.StringType
                }
            )
        ){navback->
            navback.arguments?.getString("city").let {city->
                val mainViewModel: MainViewModel= hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel = mainViewModel,city=city)
            }
        }
        composable(route = WeatherScreens.SearchScreen.name) {
            SearchScreen(navController=navController)
        }
        composable(route = WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(route = WeatherScreens.SettingScreen.name) {
            SettingScreen(navController = navController)
        }
        composable(route = WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
    }
}