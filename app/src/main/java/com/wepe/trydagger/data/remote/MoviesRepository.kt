package com.wepe.trydagger.data.remote

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.wepe.trydagger.data.database.MoviesDao
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.utils.Constants
import com.wepe.trydagger.utils.Resource
import retrofit2.Response
import javax.inject.Inject

interface MoviesRepository{
    suspend fun getMovies(page: Int, apiKey: String) : LiveData<Resource<List<ResultsMovies>>>
}
class MoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val moviesDao: MoviesDao,
    private val context: Context
): MoviesRepository {
    override suspend fun getMovies(page: Int, apiKey: String) : LiveData<Resource<List<ResultsMovies>>>{
        return object : NetworkBoundResource<List<ResultsMovies>, ResponseMovies>(gson) {
            override suspend fun saveCallResult(item: ResponseMovies) {
                item.let {
                    it.results?.forEach {movie ->
                        moviesDao.insert(movie)
                    }
                }
            }

            override fun shouldFetch(data: List<ResultsMovies>?): Boolean {
                return Constants.isConnected(context)
            }

            override suspend fun loadFromDb(): List<ResultsMovies> {
                return moviesDao.getMovies()
            }

            override suspend fun createCallAsync(): Response<ResponseMovies> {
                return apiService.getPopularMovies(apiKey, page)
            }
        }.build().asLiveData()
    }
}