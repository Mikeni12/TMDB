package com.example.tmdb.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.model.Movie
import com.example.tmdb.model.MovieDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val movieLiveData = MutableLiveData<Movie>()

    fun fetch(uuid: Int) {
        launch {
            val movie = MovieDatabase(getApplication()).movieDao().getMovie(uuid)
            movieLiveData.value = movie
        }
    }
}