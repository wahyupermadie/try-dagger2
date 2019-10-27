package com.wepe.trydagger.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.wepe.trydagger.data.model.ResultsTv

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_show")
    fun getTvShow() : DataSource.Factory<Int, ResultsTv>

    @Insert(onConflict = IGNORE)
    suspend fun insert(tv: ResultsTv) : Long

    @Query("SELECT * FROM tv_show WHERE id = :id")
    suspend fun getItem(id : Int): ResultsTv

    @Query("UPDATE tv_show SET isFavorite = :favorite WHERE id = :id")
    suspend fun setFavorite(favorite: Boolean, id : Int) : Int

    @Query("SELECT * FROM tv_show WHERE isFavorite = :favorite")
    fun getFavorite(favorite: Boolean) : DataSource.Factory<Int, ResultsTv>
}