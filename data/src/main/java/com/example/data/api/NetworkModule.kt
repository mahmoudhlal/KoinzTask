package com.example.data.api

import com.example.data.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkModule {

    private val moshi by lazy {
        val moshiBuilder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
        moshiBuilder.build()
    }

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("method" , "flickr.photos.search")
                    .addQueryParameter("format" , "json")
                    .addQueryParameter("nojsoncallback" , "50")
                    .addQueryParameter("text" , "Color")
                    .addQueryParameter("per_page" , "20")
                    .addQueryParameter("api_key", "d17378e37e555ebef55ab86c4180e8dc")
                    .build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }

    private fun getRetrofit(endpointURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun createPhotosApi(endpointURL: String): PhotosApi {
        val retrofit = getRetrofit(endpointURL)
        return retrofit.create(PhotosApi::class.java)
    }
}