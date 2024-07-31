package com.example.movieapp.cleanmovieapp.domain.usecase.getMovies


import coil.network.HttpException
import com.example.movieapp.cleanmovieapp.data.remote.dto.toMovieList
import com.example.movieapp.cleanmovieapp.domain.model.Movie
import com.example.movieapp.cleanmovieapp.domain.repository.MovieRepository
import com.example.movieapp.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository : MovieRepository) {

    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if (movieList.Response.equals("True")){
                emit(Resource.Success(movieList.toMovieList()))
            }else{
                emit(Resource.Error(message = "No movie found"))
            }
        }
        catch (e : IOError){
            emit(Resource.Error(message = "No internet connection"))
        }
        catch (e : HttpException){
            emit(Resource.Error(message = "No internet connection"))
        }
    }
}