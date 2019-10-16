package com.wepe.trydagger.ui.tv.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class TvShowPresenter @Inject constructor(
    private val tvShowDomain: TvDomain
) : BasePresenter<TvShowContract.View>(), TvShowContract.Presenter, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    var tvShow : LiveData<Resource<ResponseTv>> = MutableLiveData()
    override fun fetchTvShow(page: Int, apiKey: String) = CoroutineScope(coroutineContext).launch {

        view?.showProgressBar(true)
        withContext(Dispatchers.IO){
            tvShow = tvShowDomain.fetchTv(page, apiKey)
        }
        when(tvShow.value?.status){
            Resource.Status.SUCCESS -> {
                tvShow.value?.data?.let { view?.onTvShowSuccess(it) }
                view?.showProgressBar(false)
            }
            Resource.Status.ERROR -> {
                tvShow.value?.error?.let { view?.showError(it) }
                view?.showProgressBar(false)
            }
        }
    }
}