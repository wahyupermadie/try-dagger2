package com.wepe.trydagger.ui.favorite.tvShow

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.utils.CoroutinesContextProvider
import javax.inject.Inject

class TvShowFavVM @Inject constructor(
    private val tvDomain: TvDomain,
    private val coroutineAppContext: CoroutinesContextProvider
    ) : BaseViewModel(){
    val tvPaged : LiveData<PagedList<ResultsTv>> get() = tvDomain.fetchTvShowFavorite()
}