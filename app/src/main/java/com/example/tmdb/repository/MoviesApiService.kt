package com.example.tmdb.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MoviesApiService {

    private val BASE_URL = "https://api.themoviedb.org/4/"
    private val bearer_token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYzFhNjkzMzg1ZTRkZWNmODIzMGNhOD" +
            "llYzlkY2Y1NSIsInN1YiI6IjVkZmIwN2I0NjA5NzUwMDAxMjM0MzYzYiIsInNjb3BlcyI6WyJhcGlfcmVh" +
            "ZCJdLCJ2ZXJzaW9uIjoxfQ.p39420ms-JsQav-wHviTrqEvN1bEo0boLAUKW9KwZr0"

    private val interceptor: Interceptor = Interceptor { chain ->
        val builderToCall: Request.Builder = chain.request().newBuilder()
        builderToCall.header("Authorization", "Bearer $bearer_token")
        builderToCall.header("content-type", "application/json;charset=utf-8")
        val originalRequest: Request = builderToCall.build()
        chain.proceed(originalRequest)
    }

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(MoviesAPI::class.java)
}