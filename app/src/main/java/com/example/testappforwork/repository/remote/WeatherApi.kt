package com.example.testappforwork.repository.remote

import com.example.testappforwork.models.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?")
    fun getWeather(
        @Query("id") cityId: Int,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric"
    ): Single<WeatherResponse>
}