package com.wepe.trydagger.ui.movies.fragment

import com.wepe.trydagger.base.BaseView
import com.wepe.trydagger.data.model.ResponseMovies
import kotlinx.coroutines.Job

interface MoviesContract {

    interface View : BaseView {
        fun onMoviesSuccess(responseMovies: ResponseMovies)
    }

    interface Presenter {
        fun getMovies(page: Int, apiKey: String): Job
    }
}