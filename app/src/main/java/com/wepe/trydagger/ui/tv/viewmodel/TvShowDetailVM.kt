package com.wepe.trydagger.ui.tv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.utils.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowDetailVM @Inject constructor(
    private val tvDomain: TvDomain,
    private val coroutineContextProvider: CoroutinesContextProvider
) : BaseViewModel(){

    private var _tvShow = MutableLiveData<ResultsTv>()
    val tvShow : LiveData<ResultsTv> get() = _tvShow

    private var _updateFav_ : Int = 0
    private var _updateFav =  MutableLiveData<Int>(_updateFav_)
    val updateFav : LiveData<Int> get() = _updateFav

    private var _tv_ = ResultsTv()
    private var _tv = MutableLiveData<ResultsTv>(_tv_)
    val tv : LiveData<ResultsTv> get() = _tv

    fun addMovies(tvShow: ResultsTv){
        _tvShow.value = tvShow
    }

    fun setFavorite(fav: Boolean, moviesId: Int) {
        GlobalScope.launch(coroutineContextProvider.uiThread()){
            withContext(coroutineContextProvider.bgThread()){
                _updateFav_ = tvDomain.setFavorite(fav, moviesId)
            }
            _updateFav.value = _updateFav_
        }
    }

    fun getSingleMovie(id: Int) {
        GlobalScope.launch(coroutineContextProvider.uiThread()){
            withContext(coroutineContextProvider.bgThread()){
                _tv_ = tvDomain.fetchSingleLocalTvShow(id)
            }
            _tv.value = _tv_
        }
    }
}