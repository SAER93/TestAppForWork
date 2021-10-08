package com.example.testappforwork.di

import com.example.testappforwork.repository.remote.ImagesApi
import com.example.testappforwork.repository.remote.ImagesRetrofitClient
import com.example.testappforwork.repository.remote.WeatherApi
import com.example.testappforwork.repository.remote.WeatherRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @WeatherRetrofitClient
    fun provideWeatherOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl = request.url.newBuilder().addQueryParameter("appid", "c35880b49ff95391b3a6d0edd0c722eb").build()
            request = request.newBuilder().url(url).build()
            return@addInterceptor chain.proceed(request)
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @Singleton
    @ImagesRetrofitClient
    fun provideImagesOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @Singleton
    @ImagesRetrofitClient
    fun provideImagesRetrofit(@ImagesRetrofitClient okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://picsum.photos/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideImagesApi(@ImagesRetrofitClient retrofit: Retrofit) =
        retrofit.create(ImagesApi::class.java)

    @Provides
    @Singleton
    @WeatherRetrofitClient
    fun provideWeatherRetrofit(@WeatherRetrofitClient okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideWeatherApi(@WeatherRetrofitClient retrofit: Retrofit) =
        retrofit.create(WeatherApi::class.java)
}