package com.wepe.trydagger.di.module

import android.app.Application
import android.content.Context
import com.wepe.trydagger.external.AppContextCoroutineProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppContextCoroutineProvider() = AppContextCoroutineProvider()
}