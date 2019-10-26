package com.wepe.trydagger.di.module

import android.content.Context
import androidx.room.Room
import com.wepe.trydagger.data.database.LocalDatabase
import com.wepe.trydagger.data.database.MoviesDao
import com.wepe.trydagger.data.database.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context) : LocalDatabase =
        Room.databaseBuilder(context, LocalDatabase::class.java, "db_dicoding")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun moviesDao(localDatabase: LocalDatabase) : MoviesDao = localDatabase.moviesDao()

    @Provides
    @Singleton
    fun tvShowDao(localDatabase: LocalDatabase) : TvShowDao = localDatabase.tvShowDao()
}