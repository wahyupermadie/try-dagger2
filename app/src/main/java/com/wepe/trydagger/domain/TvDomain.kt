package com.wepe.trydagger.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
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

    fun fetchTvShowLocal() : LiveData<PagedList<ResultsTv>> {
        return tvRepositoryImpl.getTvShowLocal()
    }

    suspend fun setFavorite(fav:Boolean, id:Int): Int {
        return tvRepositoryImpl.setFavorite(fav, id)
    }

    fun fetchTvShowFavorite() : LiveData<PagedList<ResultsTv>>{
        return tvRepositoryImpl.getTvShowFavorite()
    }

    suspend fun fetchSingleLocalTvShow(id: Int) : ResultsTv{
        return tvRepositoryImpl.getSingleLocalTvShow(id)
    }
}