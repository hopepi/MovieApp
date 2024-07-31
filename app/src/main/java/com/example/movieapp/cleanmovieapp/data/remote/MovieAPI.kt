package com.example.movieapp.cleanmovieapp.data.remote

import com.example.movieapp.cleanmovieapp.data.remote.dto.MovieDetailsDto
import com.example.movieapp.cleanmovieapp.data.remote.dto.MoviesDto
import com.example.movieapp.cleanmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https://www.omdbapi.com/?i=tt3896198&apikey=*****

    //https://www.omdbapi.com/?apikey=8a05c940&s=batman


    @GET(".")
    suspend fun getMovie(
        @Query("s") search: String,
        @Query ("apikey") apiKey : String = API_KEY
    ) : MoviesDto


    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query ("apikey") apiKey : String = API_KEY
    ) : MovieDetailsDto

}