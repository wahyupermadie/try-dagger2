package com.wepe.trydagger.ui.tv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.domain.MoviesDomain
import javax.inject.Inject

class TvShowDetailVM @Inject constructor() : BaseViewModel(){

    private var _tvShow = MutableLiveData<ResultsTv>()
    val tvShow : LiveData<ResultsTv> get() = _tvShow

    fun addMovies(tvShow: ResultsTv){
        _tvShow.value = tvShow
    }
}