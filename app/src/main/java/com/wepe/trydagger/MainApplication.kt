package com.wepe.trydagger

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.wepe.trydagger.di.ApplicationComponent
import com.wepe.trydagger.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    lateinit var instance: MainApplication
    lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var mActivityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = createComponent()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return mActivityDispatchingAndroidInjector
    }

    private fun createComponent(): ApplicationComponent{
        return DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }
}