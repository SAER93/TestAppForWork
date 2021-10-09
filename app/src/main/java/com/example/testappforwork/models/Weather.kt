package com.example.testappforwork.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val weather: ArrayList<Weather>,
    val main: Main,
    @SerializedName("name") val cityName: String
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
)