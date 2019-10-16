package com.wepe.trydagger.ui.movies.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MoviesPresenter @Inject constructor(
    private val moviesDomain: MoviesDomain
) : BasePresenter<MoviesContract.View>(), MoviesContract.Presenter, CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private var movies : LiveData<Resource<ResponseMovies>> = MutableLiveData()
    override fun getMovies(page: Int, apiKey: String) = CoroutineScope(coroutineContext).launch{
            view?.showProgressBar(true)

            withContext(Dispatchers.IO) {
                movies = moviesDomain.fetchMovies(page, apiKey)
            }
            when(movies.value?.status){
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