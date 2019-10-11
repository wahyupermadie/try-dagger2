package com.wepe.trydagger.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.wepe.trydagger.ui.movies.component.MoviesComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [
    (MoviesComponent::class)])
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application
}