package com.wepe.trydagger.di.module

import com.wepe.trydagger.di.FragmentKey
import com.wepe.trydagger.ui.movies.component.MoviesComponent
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [
    (MoviesComponent::class)])
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(MoviesFragment::class)
    internal abstract fun bindMoviesComponent(factory: MoviesComponent.Factory): AndroidInjector.Factory<*>
}