package com.example.tmdb.repository

import com.example.tmdb.model.ResponseGeneral
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {
    @GET("account/5dfb07b4609750001234363b/movie/{type}")
    suspend fun getMovies(
        @Path("type") type: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "es"
    ): ResponseGeneral
}