package com.wepe.trydagger.ui.tv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.utils.CoroutinesContextProvider
import com.wepe.trydagger.utils.ErrorHandler
import com.wepe.trydagger.utils.Event
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineContext
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
    private val tvDomain: TvDomain,
    private val coroutineAppContext: CoroutinesContextProvider
) : BaseViewModel(){

    private val _tvShow = MediatorLiveData<ResponseTv>()
    val tvShow : LiveData<ResponseTv> = _tvShow

    private var tvShowSource: LiveData<Resource<ResponseTv>> = MutableLiveData<Resource<ResponseTv>>()

    fun getTvShow(page : Int) = viewModelScope.launch(coroutineAppContext.uiThread()){
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
                }
                Resource.Status.ERROR -> {
                    _errorHandler.value = Event(ErrorHandler(it.error, ErrorHandler.ErrorType.SNACKBAR))
                    _loadingHandler.value = false
                }
            }
        }
    }
}