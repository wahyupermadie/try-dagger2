package com.wepe.trydagger.ui.tv.module

import android.content.Context
import com.google.gson.Gson
import com.wepe.trydagger.data.database.TvShowDao
import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.data.remote.TvRepositoryImpl
import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.domain.TvDomain
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @Provides
    @FragmentScope
    fun tvRepository(apiService: ApiService, context: Context, tvShowDao: TvShowDao, gson: Gson ): TvRepositoryImpl
            = TvRepositoryImpl(apiService, context = context, gson = gson, tvShowDao = tvShowDao)

    @Provides
    @FragmentScope
    fun tvDomain(repository: TvRepositoryImpl): TvDomain = TvDomain(repository)
}