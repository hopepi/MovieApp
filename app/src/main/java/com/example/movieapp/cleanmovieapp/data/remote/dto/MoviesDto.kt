package com.example.movieapp.cleanmovieapp.data.remote.dto

import com.example.movieapp.cleanmovieapp.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun MoviesDto.toMovieList() : List<Movie> {
    return Search.map { Search -> Movie(Search.Poster,Search.Title,Search.Year,Search.imdbID) }
}