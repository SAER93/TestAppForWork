package com.example.testappforwork.repository.remote

import com.example.testappforwork.models.Image
import com.example.testappforwork.models.ImagesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("list?")
    fun getImageList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<ArrayList<Image>>
}