package com.example.testappforwork.repository.remote

import com.example.testappforwork.models.Image
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("list?")
    fun getImageList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<ArrayList<Image>>
}