package com.example.movieapp.cleanmovieapp.presentation.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.cleanmovieapp.domain.usecase.getMovies.GetMoviesUseCase
import com.example.movieapp.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state : State<MoviesState> = _state

    /*
    Sürekli arama yapılmasını istiyorsak yani her tazdığımızada dinamik şekilde değişmesini istiyorsak
    öncek jobı iptal etmemeiz gerekir
     */
    private var job : Job? = null

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search : String){
        job?.cancel()

        job = getMoviesUseCase.executeGetMovies(search = search).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = MoviesState(error = it.message ?: "Error")
                }
                is Resource.Loading ->{
                    _state.value = MoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search ->{
                getMovies(event.searchString)
            }
        }
    }
}