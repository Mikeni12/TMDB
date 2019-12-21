package com.example.tmdb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tmdb.utils.DynamicSearchAdapter
import com.squareup.moshi.Json

@Entity
data class Movie(
    @ColumnInfo(name = "movie_id")
    @field:Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "original_language")
    @field:Json(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    @field:Json(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "title")
    @field:Json(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    @field:Json(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    @field:Json(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "poster_path")
    @field:Json(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "adult")
    @field:Json(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "vote_average")
    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    @field:Json(name = "vote_count")
    val voteCount: Int
) : DynamicSearchAdapter.Searchable {

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

    @ColumnInfo(name = "type")
    var type: String? = null

    override fun getSearchCriteria(): String = title.toLowerCase()
}