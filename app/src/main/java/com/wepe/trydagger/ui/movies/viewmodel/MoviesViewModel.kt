package com.wepe.trydagger.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.ErrorHandler
import com.wepe.trydagger.utils.Event
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesDomain: MoviesDomain
) : BaseViewModel(){

    private val _movies = MediatorLiveData<ResponseMovies>()
    val movies : LiveData<ResponseMovies> = _movies

    private var moviesSource: LiveData<Resource<ResponseMovies>> = MutableLiveData<Resource<ResponseMovies>>()

    fun getMovies(page : Int) = viewModelScope.launch(Dispatchers.Main){
        _loadingHandler.value = true
        _movies.postValue(null)
        _movies.removeSource(moviesSource)
        withContext(Dispatchers.IO){
            moviesSource = moviesDomain.fetchMovies(page, BuildConfig.API_KEY)
        }
        _movies.addSource(moviesSource){
           when(it.status){
               Resource.Status.SUCCESS -> {
                    _movies.value = it.data
                    _loadingHandler.value = false
               }
               Resource.Status.ERROR -> {
                   _errorHandler.value = Event(ErrorHandler(it.error, ErrorHandler.ErrorType.SNACKBAR))
                   _loadingHandler.value = false
               }
           }
        }
    }
}