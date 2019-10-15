package com.wepe.trydagger.viewmodeltest.di

import com.wepe.trydagger.di.module.NetworkModule
import com.wepe.trydagger.ui.movies.module.MoviesModule
import com.wepe.trydagger.viewmodeltest.base.BaseTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,  MoviesModule::class])
interface TestAppComponent{
    fun inject(baseTest : BaseTest)
}