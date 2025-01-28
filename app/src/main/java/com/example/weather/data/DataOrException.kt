package com.example.weather.data

class DataOrException<T, Boolean, E: Exception>(
    val data :T?=null,
    var isDataLoading: Boolean?=null,
    val exception: E?=null
)