package com.example.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.example.data.entities.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotos(photos: List<PhotoEntity>)

    @Query("SELECT * FROM photo WHERE page = :pageNum")
    suspend fun getSavedPhotos(pageNum : Int): List<PhotoEntity>
}