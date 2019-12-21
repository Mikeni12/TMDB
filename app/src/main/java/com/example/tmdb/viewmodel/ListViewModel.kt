package com.example.tmdb.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.model.Movie
import com.example.tmdb.repository.MoviesRepository
import kotlinx.coroutines.launch
import java.io.IOException

class ListViewModel(application: Application) : BaseViewModel(application) {

    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val moviesRepository = MoviesRepository(application)

    fun refresh(type: String) {
        launch {
            val movies = moviesRepository.getMoviesFromDatabase(type)
            if (movies.isEmpty()){
                fetchFromRemote(type)
            } else {
                fetchFromDatabase(type)
            }
        }
    }

    fun refreshFromRemote(type: String){
        fetchFromRemote(type)
    }

    private fun fetchFromRemote(type: String) {
        loading.value = true
        launch {
            try {
                val response = moviesRepository.getMoviesFromRemote(type)
                if (response.results.isNotEmpty()) {
                    val movies = moviesRepository.storeMoviesLocally(response.results, type)
                    moviesRetrieved(movies)
                } else {
                    moviesLoadError.value = true
                    loading.value = false
                }
            } catch (e: IOException) {
                Log.d("HTTP", e.localizedMessage ?: "IO Exception")
                fetchFromDatabase(type)
            } catch (e: Throwable) {
                Log.d("HTTP", e.localizedMessage ?: "Throwable")
                moviesLoadError.value = true
                loading.value = false
            }
        }
    }

    private fun fetchFromDatabase(type: String) {
        loading.value = true
        launch {
            val movies = moviesRepository.getMoviesFromDatabase(type)
            if (movies.isNotEmpty()) {
                moviesRetrieved(movies)
            } else {
                moviesLoadError.value = true
                loading.value = false
            }
        }
    }

    private fun moviesRetrieved(moviesList: List<Movie>) {
        movies.value = moviesList
        moviesLoadError.value = false
        loading.value = false
    }
}