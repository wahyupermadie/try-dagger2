package com.wepe.trydagger.data.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.wepe.trydagger.data.database.TvShowDao
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.utils.Constants
import com.wepe.trydagger.utils.Resource
import retrofit2.Response
import javax.inject.Inject

interface TvRepository{
    suspend fun getPopularTv(page: Int, apiKey: String) : LiveData<Resource<List<ResultsTv>>>
}

private val tvResource = MutableLiveData<Resource<ResponseTv>>()
class TvRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson,
    private val tvShowDao: TvShowDao,
    private val context: Context
) : TvRepository{
    override suspend fun getPopularTv(page: Int, apiKey: String): LiveData<Resource<List<ResultsTv>>> {
        return object : NetworkBoundResource<List<ResultsTv>, ResponseTv>(gson) {
            override suspend fun saveCallResult(item: ResponseTv) {
                item.let {
                    it.results?.forEach {tv ->
                        tvShowDao.insert(tv)
                    }
                }
            }

            override fun shouldFetch(data: List<ResultsTv>?): Boolean {
                return Constants.isConnected(context)
            }

            override suspend fun loadFromDb(): List<ResultsTv> {
                return tvShowDao.getTvShow()
            }

            override suspend fun createCallAsync(): Response<ResponseTv> {
                return apiService.getPopularTv(apiKey, page)
            }
        }.build().asLiveData()
    }
}