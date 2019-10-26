package com.wepe.trydagger.ui.tv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.utils.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
    private val tvDomain: TvDomain,
    private val coroutineAppContext: CoroutinesContextProvider
) : BaseViewModel(){

    private val _tvShow = MediatorLiveData<List<ResultsTv>>()
    val tvShow : LiveData<List<ResultsTv>> = _tvShow

    private var tvShowSource: LiveData<Resource<List<ResultsTv>>> = MutableLiveData<Resource<List<ResultsTv>>>()

    fun getTvShow(page : Int) = viewModelScope.launch(coroutineAppContext.uiThread()){
        EspressoIdlingResource.increment()
        _loadingHandler.value = true
        _tvShow.postValue(null)
        _tvShow.removeSource(tvShowSource)
        withContext(coroutineAppContext.bgThread()){
            tvShowSource = tvDomain.fetchTv(page, BuildConfig.API_KEY)
        }
        _tvShow.addSource(tvShowSource){
            when(it.status){
                Resource.Status.SUCCESS -> {
                    _tvShow.value = it.data
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