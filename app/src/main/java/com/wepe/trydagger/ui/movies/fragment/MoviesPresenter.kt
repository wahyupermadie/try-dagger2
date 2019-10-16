package com.wepe.trydagger.ui.movies.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.base.BasePresenter
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MoviesPresenter @Inject constructor(
    private val moviesDomain: MoviesDomain
) : BasePresenter<MoviesContract.View>(), MoviesContract.Presenter, CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private var movies : LiveData<Resource<ResponseMovies>> = MutableLiveData()
    override fun getMovies(page: Int, apiKey: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val movies = async { moviesDomain.fetchMovies(page, apiKey) }
            Log.d("DATA_GUEP","DATA "+movies.await())
        }

    }
}