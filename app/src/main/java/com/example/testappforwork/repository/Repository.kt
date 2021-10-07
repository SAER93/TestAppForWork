package com.example.testappforwork.repository

import com.example.testappforwork.models.ErrorResult
import com.example.testappforwork.models.Image
import com.example.testappforwork.models.SuccessResult
import com.example.testappforwork.models.Result
import com.example.testappforwork.repository.remote.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofitService: RetrofitService
) {
    private val compositeDisposable = CompositeDisposable()

    fun getImages(
        page: Int,
        limit: Int,
        onResult: (Result<ArrayList<Image>>) -> Unit
    ) {
        compositeDisposable.add(
            retrofitService.getImageList(page, limit)
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