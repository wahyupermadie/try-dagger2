package com.wepe.trydagger.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

interface MoviesRepository{
    suspend fun getMovies(page: Int, apiKey: String) : LiveData<Resource<ResponseMovies>>
}

private var moviesResource = MutableLiveData<Resource<ResponseMovies>>()
class MoviesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): MoviesRepository {
    override suspend fun getMovies(page: Int, apiKey: String) : LiveData<Resource<ResponseMovies>>{
            val response = apiService.getPopularMovies(apiKey, page)
            withContext(Dispatchers.Main) {
                Log.d("DATA_GUEX ","HELLO BOSKUX"+response.body())
                try {
                    if (response.isSuccessful) {
                        moviesResource.value = Resource.success(response.body())
                    } else {
                        moviesResource.value = Resource.error("Error code "+response.code().toString(), response.body())
                    }
                } catch (e: HttpException) {
                    moviesResource.value = Resource.error("Exception ${e.message}", response.body())
                } catch (e: Throwable) {
                    moviesResource.value = Resource.error("Ooops: Something else went wrong", response.body())
                }
            }
        Log.d("DATA_GUEX ","HELLO BOSKUX"+moviesResource.value)
        return moviesResource
    }
}