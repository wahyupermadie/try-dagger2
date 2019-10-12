package com.wepe.trydagger.di.module

import com.wepe.trydagger.di.scope.ActivityScope
import dagger.Module
import com.wepe.trydagger.ui.MainActivity
import dagger.Binds
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}