package com.wepe.trydagger.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.wepe.trydagger.data.model.ResultsMovies

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies() : DataSource.Factory<Int, ResultsMovies>

    @Insert(onConflict = IGNORE)
    suspend fun insert(movies: ResultsMovies) : Long

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getItem(id : Int): ResultsMovies

    @Query("UPDATE movies SET isFavorite = :favorite WHERE id = :id")
    suspend fun setFavorite(favorite: Boolean, id : Int) : Int

    @Query("SELECT * FROM movies WHERE isFavorite = :favorite")
    fun getFavorite(favorite: Boolean) : DataSource.Factory<Int, ResultsMovies>
}