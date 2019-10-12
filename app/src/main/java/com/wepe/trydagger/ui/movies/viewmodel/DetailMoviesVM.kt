package com.wepe.trydagger.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.domain.MoviesDomain
import javax.inject.Inject

class DetailMoviesVM @Inject constructor(
    private val moviesDomain: MoviesDomain
) : BaseViewModel(){

    private var _movies = MutableLiveData<ResultsMovies>()
    val movies : LiveData<ResultsMovies> get() = _movies

    fun addMovies(movies: ResultsMovies){
        _movies.value = movies
    }
}