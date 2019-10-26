package com.wepe.trydagger.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.data.remote.TvRepositoryImpl
import com.wepe.trydagger.utils.Resource
import javax.inject.Inject

class TvDomain @Inject constructor(private val tvRepositoryImpl: TvRepositoryImpl){

    suspend fun fetchTv(page : Int, apiKey: String) : LiveData<Resource<List<ResultsTv>>> {
        return Transformations.map(tvRepositoryImpl.getPopularTv(page, apiKey)){
            it
        }
    }
}