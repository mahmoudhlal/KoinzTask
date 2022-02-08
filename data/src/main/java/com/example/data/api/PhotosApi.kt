package com.example.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("rest/")
    suspend fun getPhotos(@Query("page") page: Int): Response<PhotosApiResponse>

}