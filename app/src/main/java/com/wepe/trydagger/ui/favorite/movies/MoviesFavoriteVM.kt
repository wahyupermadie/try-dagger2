package com.wepe.trydagger.ui.favorite.movies

import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.domain.MoviesDomain
import com.wepe.trydagger.utils.CoroutinesContextProvider
import javax.inject.Inject

class MoviesFavoriteVM @Inject constructor(
    private val moviesDomain: MoviesDomain,
    private val coroutineContextProvider: CoroutinesContextProvider
    ) : BaseViewModel(){

    val pagedMovies = moviesDomain.fetchMoviesFavorite()
}