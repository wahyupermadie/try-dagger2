package com.wepe.trydagger.domain

import androidx.lifecycle.LiveData
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.data.remote.TvRepositoryImpl
import com.wepe.trydagger.utils.Resource
import javax.inject.Inject

class TvDomain @Inject constructor(private val tvRepositoryImpl: TvRepositoryImpl){

    suspend fun fetchTv(page : Int, apiKey: String) : LiveData<Resource<ResponseTv>> {
        return tvRepositoryImpl.getPopularTv(page, apiKey).apply {
//            for transformation soon
        }
    }
}