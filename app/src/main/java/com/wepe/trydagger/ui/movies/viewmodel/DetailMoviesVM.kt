package com.wepe.trydagger.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailMoviesVM @Inject constructor(
    private val moviesDomain: MoviesDomain,
    private val coroutineContextProvider: CoroutinesContextProvider
    ) : BaseViewModel(){

    private var _movies = MutableLiveData<ResultsMovies>()
    val movies : LiveData<ResultsMovies> get() = _movies

    private var _updateFav_ : Int = 0
    private var _updateFav =  MutableLiveData<Int>(_updateFav_)
    val updateFav : LiveData<Int> get() = _updateFav

    private var _movie_ = ResultsMovies()
    private var _movie = MutableLiveData<ResultsMovies>(_movie_)
    val movie : LiveData<ResultsMovies> get() = _movie

    fun addMovies(movies: ResultsMovies){
        _movies.value = movies
    }

    fun setFavorite(fav: Boolean, moviesId: Int) {
        GlobalScope.launch(coroutineContextProvider.uiThread()){
            withContext(coroutineContextProvider.bgThread()){
                _updateFav_ = moviesDomain.setFavorite(fav, moviesId)
            }
            _updateFav.value = _updateFav_
        }
    }

    fun getSingleMovie(id: Int) {
        GlobalScope.launch(coroutineContextProvider.uiThread()){
            withContext(coroutineContextProvider.bgThread()){
                _movie_ = moviesDomain.fetchSingleLocalMovie(id)
            }
            _movie.value = _movie_
        }
    }
}