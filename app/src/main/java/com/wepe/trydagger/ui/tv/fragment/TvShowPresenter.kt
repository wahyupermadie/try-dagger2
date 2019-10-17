package com.wepe.trydagger.ui.tv.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.external.ContextCoroutineProvider
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TvShowPresenter @Inject constructor(
    private val tvShowDomain: TvDomain,
    private val coroutineContextProvider: ContextCoroutineProvider
) : BasePresenter<TvShowContract.View>(), TvShowContract.Presenter {
    var tvShow : LiveData<Resource<ResponseTv>> = MutableLiveData()
    override fun fetchTvShow(page: Int, apiKey: String) = CoroutineScope(coroutineContextProvider.uiDispatcher()).launch {

        view?.showProgressBar(true)
        withContext(coroutineContextProvider.bgDispatcher()){
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