package com.wepe.trydagger.di

import android.app.Application
import com.wepe.trydagger.MainApplication
import com.wepe.trydagger.di.module.AppModule
import com.wepe.trydagger.di.module.FragmentBuilder
import com.wepe.trydagger.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (FragmentBuilder::class),
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