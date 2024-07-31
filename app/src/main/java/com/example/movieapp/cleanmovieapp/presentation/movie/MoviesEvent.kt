package com.example.movieapp.cleanmovieapp.presentation.movie

sealed class MoviesEvent {
    data class Search(val searchString: String) : MoviesEvent()
}