package com.wepe.trydagger.utils

import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.model.ResponseTv

object ResponseFakeMovies {

    val FAKE_MOVIES = ResponseMovies(
        page = 1,
        totalResults = 10000,
        totalPages = 500,
        results = null
    ) as ResponseMovies

    val FAKE_TV = ResponseTv(
        page = 1,
        totalResults = 10000,
        totalPages = 500,
        results = null
    ) as ResponseTv
}