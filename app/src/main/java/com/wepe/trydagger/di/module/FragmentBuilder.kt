package com.wepe.trydagger.di.module

import androidx.fragment.app.Fragment
import com.wepe.trydagger.di.FragmentKey
import com.wepe.trydagger.ui.movies.component.MoviesComponent
import com.wepe.trydagger.ui.movies.list.MoviesFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {
    @Binds
    @IntoMap
    @FragmentKey(MoviesFragment::class)
    internal abstract fun bindMoviesList(builder: MoviesComponent.Builder): AndroidInjector.Factory<out Fragment>
}