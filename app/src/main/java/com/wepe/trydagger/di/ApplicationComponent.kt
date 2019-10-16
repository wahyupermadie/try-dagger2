package com.wepe.trydagger.di

import android.app.Application
import com.wepe.trydagger.MainApplication
import com.wepe.trydagger.di.module.ActivityBuilder
import com.wepe.trydagger.di.module.AppModule
import com.wepe.trydagger.di.module.FragmentBuilder
import com.wepe.trydagger.di.module.NetworkModule
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
import com.wepe.trydagger.ui.tv.fragment.TvShowFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (ActivityBuilder::class),
    (NetworkModule::class),
    (AppModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: MainApplication)
}