package com.example.movieapp.cleanmovieapp.domain.usecase.getMoviesDetails

import coil.network.HttpException
import com.example.movieapp.cleanmovieapp.data.remote.dto.toMovieDetail
import com.example.movieapp.cleanmovieapp.domain.model.MovieDetail
import com.example.movieapp.cleanmovieapp.domain.repository.MovieRepository
import com.example.movieapp.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val repository: MovieRepository) {


    fun executeGetMoviesDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        }
        catch (e : IOError){
            emit(Resource.Error(message = "No internet connection"))
        }
        catch (e : HttpException){
            emit(Resource.Error(message = "No internet connection"))
        }
    }

}