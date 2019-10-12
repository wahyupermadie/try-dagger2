package com.wepe.trydagger.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesDomain: MoviesDomain
) : BaseViewModel(){

    private val _movies = MediatorLiveData<Resource<ResponseMovies>>()
    val movies : LiveData<Resource<ResponseMovies>> = _movies

    private var moviesSource: LiveData<Resource<ResponseMovies>> = MutableLiveData<Resource<ResponseMovies>>()

    fun getMovies(page : Int) = viewModelScope.launch(Dispatchers.Main){
        _movies.postValue(null)
        _movies.removeSource(moviesSource)
        withContext(Dispatchers.IO){
            moviesSource = moviesDomain.fetchMovies(page, BuildConfig.API_KEY)
        }
        _movies.addSource(moviesSource){
            _movies.value = it
        }
    }
}