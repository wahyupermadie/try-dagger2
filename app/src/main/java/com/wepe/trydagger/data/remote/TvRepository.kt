package com.wepe.trydagger.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

interface TvRepository{
    suspend fun getPopularTv(page: Int, apiKey: String) : LiveData<Resource<ResponseTv>>
}

private val tvResource = MutableLiveData<Resource<ResponseTv>>()
class TvRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TvRepository{
    override suspend fun getPopularTv(page: Int, apiKey: String): LiveData<Resource<ResponseTv>> {
        val response = apiService.getPopularTv(apiKey, page)
        withContext(Dispatchers.Main) {
            try {
                if (response.isSuccessful) {
                    tvResource.value = Resource.success(response.body())
                } else {
                    tvResource.value = Resource.error("Error code "+response.code().toString(), response.body())
                }
            } catch (e: HttpException) {
                tvResource.value = Resource.error("Exception ${e.message}", response.body())
            } catch (e: Throwable) {
                tvResource.value = Resource.error("Ooops: Something else went wrong", response.body())
            }
        }
        return tvResource
    }
}