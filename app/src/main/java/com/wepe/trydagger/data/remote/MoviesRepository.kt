package com.wepe.trydagger.data.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
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
    suspend fun getMovies(page: Int, apiKey: String) : LiveData<Resource<PagedList<ResultsMovies>>>
}
class MoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val moviesDao: MoviesDao,
    private val context: Context
): MoviesRepository {
    override suspend fun getMovies(page: Int, apiKey: String) : LiveData<Resource<PagedList<ResultsMovies>>>{
        return object : NetworkBoundResource<PagedList<ResultsMovies>, ResponseMovies>(gson) {
            override suspend fun saveCallResult(item: ResponseMovies) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: PagedList<ResultsMovies>?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override suspend fun loadFromDb(): PagedList<ResultsMovies> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override suspend fun createCallAsync(): Response<ResponseMovies> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.build().asLiveData()
    }
}