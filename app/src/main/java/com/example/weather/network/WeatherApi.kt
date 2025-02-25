package com.example.weather.network

import com.example.weather.model.Weather
import com.example.weather.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET("data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q")query: String,
        @Query("units")units: String = "imperial",
        @Query("appid")apiKey: String=API_KEY
    ): Weather
}