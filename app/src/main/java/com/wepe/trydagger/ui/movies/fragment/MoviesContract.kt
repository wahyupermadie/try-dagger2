package com.wepe.trydagger.ui.movies.fragment

import com.wepe.trydagger.base.BaseView
import com.wepe.trydagger.data.model.ResponseMovies

interface MoviesContract {

    interface View : BaseView {
        fun onMoviesSuccess(responseMovies: ResponseMovies)
        fun onMoviesError(message: String)
        fun onMoviesLoading(loadingState: Boolean)
    }

    interface Presenter {
        fun getMovies(page: Int, apiKey: String)
    }
}