package com.example.data.repositories.photos

import com.example.domain.entities.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosLocalDataSource {
    suspend fun savePhotos(photos : List<Photo>)
    suspend fun getPhotos() : Flow<List<Photo>>
}