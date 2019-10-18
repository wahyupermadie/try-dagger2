package com.wepe.trydagger.utils


/**
 * Created by ivanaazuka on 2019-10-18.
 * Android Engineer
 */

class AppConstants {

    /**
     * TMDB API Endpoint
     */
    interface TMDBAPI {
        companion object {
            const val POPULAR_MOVIE = "movie/popular"
            const val POPULAR_TV_SHOW = "tv/popular"
        }
    }
}