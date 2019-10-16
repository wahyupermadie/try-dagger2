package com.wepe.trydagger.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.remote.MoviesRepositoryImpl
import com.wepe.trydagger.utils.Resource
import javax.inject.Inject

class MoviesDomain @Inject constructor(private val moviesRepositoryImpl: MoviesRepositoryImpl){

    suspend fun fetchMovies(page : Int, apiKey: String) : LiveData<Resource<ResponseMovies>>{
        return moviesRepositoryImpl.getMovies(page, apiKey).apply {
//            for transformation
        }
    }
}