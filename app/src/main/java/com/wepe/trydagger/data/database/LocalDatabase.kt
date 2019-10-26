package com.wepe.trydagger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.data.model.ResultsTv

@Database(entities = arrayOf(ResultsMovies::class,
    ResultsTv::class), version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun moviesDao() : MoviesDao
    abstract fun tvShowDao() : TvShowDao
}