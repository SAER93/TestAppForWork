package com.example.testappforwork.repository

import com.example.testappforwork.models.*
import com.example.testappforwork.repository.remote.ImagesRetrofitClient
import com.example.testappforwork.repository.remote.ImagesApi
import com.example.testappforwork.repository.remote.WeatherApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofitImages: ImagesApi,
    private val retrofitWeather: WeatherApi
) {
    private val compositeDisposable = CompositeDisposable()

    fun getImages(
        page: Int,
        limit: Int,
        onResult: (Result<ArrayList<Image>>) -> Unit
    ) {
        compositeDisposable.add(
            retrofitImages.getImageList(page, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ images ->
                    onResult(SuccessResult(images))
                }, {
                    onResult(ErrorResult())
                })
        )
    }

    fun getWeather(
        cityId: Int,
        onResult: (Result<WeatherResponse>) -> Unit
    ) {
        compositeDisposable.add(
            retrofitWeather.getWeather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ images ->
                    onResult(SuccessResult(images))
                }, {
                    onResult(ErrorResult())
                })
        )
    }

    fun clear() {
        compositeDisposable.dispose()
    }
}