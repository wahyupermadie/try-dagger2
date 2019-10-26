package com.wepe.trydagger.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.wepe.trydagger.data.model.ResultsMovies

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovies() : List<ResultsMovies>

    @Insert(onConflict = REPLACE)
    suspend fun insert(movies: ResultsMovies) : Long
}