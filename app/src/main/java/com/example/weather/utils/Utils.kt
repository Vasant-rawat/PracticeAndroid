package com.example.weather.utils

import java.text.SimpleDateFormat
import java.util.Date


fun formatDate(timestamp: Int):String{
val sdf=SimpleDateFormat("EEE,MMM d")
    val date=Date(timestamp.toLong()*1000)
    return sdf.format(date)
}

fun formatDateTime(timestamp: Int):String{
    val sdf=SimpleDateFormat("hh:mm:aa")
    val date=Date(timestamp.toLong()*1000)
    return sdf.format(date)
}
fun formatDecimals(item:Double):String{
    return " %.0f".format(item)
}
fun fahrenheitToCelsius(fahrenheit: Double): Double {
    return (fahrenheit - 32) * 5 / 9
}