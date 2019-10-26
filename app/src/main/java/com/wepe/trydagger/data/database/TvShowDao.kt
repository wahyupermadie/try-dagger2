package com.wepe.trydagger.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.wepe.trydagger.data.model.ResultsTv

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tv_show")
    suspend fun getTvShow() : List<ResultsTv>

    @Insert(onConflict = REPLACE)
    suspend fun insert(tv: ResultsTv) : Long
}