package com.example.tmdb.repository

import android.content.Context
import com.example.tmdb.model.Movie
import com.example.tmdb.model.MovieDatabase

class MoviesRepository(val context: Context) {

    private val moviesServiceApi = MoviesApiService().api

    suspend fun getMoviesFromRemote(type: String) = moviesServiceApi.getMovies(type)

    suspend fun getMoviesFromDatabase(type: String) =
        MovieDatabase(this.context).movieDao().getAllMoviesByType(type)

    suspend fun storeMoviesLocally(moviesList: List<Movie>, type: String): List<Movie> {
        val dao = MovieDatabase(this.context).movieDao()
        dao.deleteAllMovies(type)
        moviesList.forEach { it.type = type }
        val result = dao.insertAll(*moviesList.toTypedArray())
        var i = 0
        while (i < moviesList.size) {
            moviesList[i].uuid = result[i].toInt()
            ++i
        }
        return moviesList
    }
}