package com.wepe.trydagger.data.network

import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(AppConstants.TMDBAPI.POPULAR_MOVIE)
    suspend fun getPopularMovies(@Query("api_key") key : String,
                                 @Query("page") page : Int) : Response<ResponseMovies>

    @GET(AppConstants.TMDBAPI.POPULAR_TV_SHOW)
    suspend fun getPopularTv(@Query("api_key") key : String,
                     @Query("page") page : Int) : Response<ResponseTv>
}