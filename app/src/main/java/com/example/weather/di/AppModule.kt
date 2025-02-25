package com.example.weather.di

import com.example.weather.network.WeatherApi
import com.example.weather.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
//calls a retrofit builder (Which returns RetrofitObject) a weather api


/*this will create a class using  interface using hilt in java for dependency injection */

    fun provideOpenWeatherApi():WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

}