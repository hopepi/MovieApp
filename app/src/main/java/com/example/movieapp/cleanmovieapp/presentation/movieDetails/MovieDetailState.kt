package com.example.movieapp.cleanmovieapp.presentation.movieDetails

import com.example.movieapp.cleanmovieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : MovieDetail? = null,
    val error : String = ""
)