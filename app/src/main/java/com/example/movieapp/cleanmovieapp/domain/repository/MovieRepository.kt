package com.example.movieapp.cleanmovieapp.domain.repository

import com.example.movieapp.cleanmovieapp.data.remote.dto.MovieDetailsDto
import com.example.movieapp.cleanmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search : String) : MoviesDto

    suspend fun getMovieDetail (imdbId : String) : MovieDetailsDto
}