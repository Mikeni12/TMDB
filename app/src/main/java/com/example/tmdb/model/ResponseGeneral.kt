package com.example.tmdb.model

data class ResponseGeneral(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)