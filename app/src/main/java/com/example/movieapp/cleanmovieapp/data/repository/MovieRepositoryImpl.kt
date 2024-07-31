package com.example.movieapp.cleanmovieapp.data.repository

import com.example.movieapp.cleanmovieapp.data.remote.MovieAPI
import com.example.movieapp.cleanmovieapp.data.remote.dto.MovieDetailsDto
import com.example.movieapp.cleanmovieapp.data.remote.dto.MoviesDto
import com.example.movieapp.cleanmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovie(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailsDto {
        return api.getMovieDetail(imdbId)
    }
}