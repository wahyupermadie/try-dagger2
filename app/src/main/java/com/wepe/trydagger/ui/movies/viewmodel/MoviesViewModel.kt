package com.wepe.trydagger.ui.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.database.MoviesDao
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesDomain: MoviesDomain,
    private val coroutineContextProvider: CoroutinesContextProvider,
    private val moviesDao: MoviesDao
) : BaseViewModel(){

    private val _movies = MediatorLiveData<List<ResultsMovies>>()
    val movies : LiveData<List<ResultsMovies>> = _movies

    private var moviesSource: LiveData<Resource<List<ResultsMovies>>> = MutableLiveData<Resource<List<ResultsMovies>>>()

    fun getMovies(page : Int) = viewModelScope.launch(coroutineContextProvider.uiThread()){
        EspressoIdlingResource.increment()
        _loadingHandler.value = true
        _movies.postValue(null)
        _movies.removeSource(moviesSource)
        withContext(coroutineContextProvider.bgThread()){
            moviesSource = moviesDomain.fetchMovies(page, BuildConfig.API_KEY)
        }
        _movies.addSource(moviesSource){
           when(it.status){
               Resource.Status.SUCCESS -> {
                   _movies.value = it.data
                   _loadingHandler.value = false
                   if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow){
                       EspressoIdlingResource.decrement()
                   }
               }
               Resource.Status.ERROR -> {
                   _errorHandler.value = Event(ErrorHandler(it.error, ErrorHandler.ErrorType.SNACKBAR))
                   _loadingHandler.value = false
                   if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow){
                       EspressoIdlingResource.decrement()
                   }
               }
           }
        }
    }
}