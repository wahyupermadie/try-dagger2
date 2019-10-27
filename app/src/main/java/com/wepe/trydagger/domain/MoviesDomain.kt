package com.wepe.trydagger.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
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

    fun fetchMoviesLocal() : LiveData<PagedList<ResultsMovies>> {
        return moviesRepositoryImpl.getMoviesLocal()
    }

    suspend fun setFavorite(fav:Boolean, id:Int): Int {
        return moviesRepositoryImpl.setFavorite(fav, id)
    }

    fun fetchMoviesFavorite() : LiveData<PagedList<ResultsMovies>>{
        return moviesRepositoryImpl.getMoviesFavorite()
    }

    suspend fun fetchSingleLocalMovie(id: Int) : ResultsMovies{
        return moviesRepositoryImpl.getSingleLocalMovies(id)
    }
}

