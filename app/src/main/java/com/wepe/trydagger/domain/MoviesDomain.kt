package com.wepe.trydagger.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.data.remote.MoviesRepositoryImpl
import com.wepe.trydagger.utils.Resource
import javax.inject.Inject

class MoviesDomain @Inject constructor(private val moviesRepositoryImpl: MoviesRepositoryImpl){

    suspend fun fetchMovies(page : Int, apiKey: String) : LiveData<Resource<List<ResultsMovies>>> {
        return Transformations.map(moviesRepositoryImpl.getMovies(page, apiKey)){
            it
        }
    }
}