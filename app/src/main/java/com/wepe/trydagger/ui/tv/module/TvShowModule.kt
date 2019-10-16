package com.wepe.trydagger.ui.tv.module

import com.wepe.trydagger.data.network.ApiService
import com.wepe.trydagger.data.remote.TvRepositoryImpl
import com.wepe.trydagger.di.scope.FragmentScope
import com.wepe.trydagger.domain.TvDomain
import com.wepe.trydagger.ui.tv.fragment.TvShowPresenter
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @Provides
    @FragmentScope
    fun tvRepository(apiService: ApiService): TvRepositoryImpl = TvRepositoryImpl(apiService)

    @Provides
    @FragmentScope
    fun tvDomain(repository: TvRepositoryImpl): TvDomain = TvDomain(repository)

    @Provides
    @FragmentScope
    fun tvPresenter(tvDomain: TvDomain) : TvShowPresenter = TvShowPresenter(tvDomain)
}