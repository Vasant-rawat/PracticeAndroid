package com.example.weather.repository

import com.example.weather.data.DataOrException
import com.example.weather.model.Weather
import com.example.weather.network.WeatherApi
import javax.inject.Inject

class WeatherRepository  @Inject constructor(private val api: WeatherApi) {
    suspend fun getWeather(cityQuery: String ):DataOrException<Weather,Boolean,Exception>{
        val response= try{
            api.getWeather(query =cityQuery)
        }catch (e:Exception){
            return DataOrException(exception=e)
        }

        return DataOrException(data=response)
    }
}