package com.wepe.trydagger.ui.movies.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.external.ContextCoroutineProvider
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MoviesPresenter @Inject constructor(
    private val moviesDomain: MoviesDomain,
    private val coroutineContextProvider: ContextCoroutineProvider
) : BasePresenter<MoviesContract.View>(), MoviesContract.Presenter {
    private var movies : LiveData<Resource<ResponseMovies>> = MutableLiveData()
    override fun getMovies(page: Int, apiKey: String) =
        CoroutineScope(coroutineContextProvider.uiDispatcher()).launch {
            view?.showProgressBar(true)

            withContext(coroutineContextProvider.bgDispatcher()) {
                movies = moviesDomain.fetchMovies(page, apiKey)
            }
            when (movies.value?.status) {
                Resource.Status.SUCCESS -> {
                    movies.value?.data?.let { view?.onMoviesSuccess(it) }
                    view?.showProgressBar(false)
                }
                Resource.Status.ERROR -> {
                    movies.value?.error?.let { view?.showError(it) }
                    view?.showProgressBar(false)
                }
            }
        }
}