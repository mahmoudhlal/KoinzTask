package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.entities.Photo
import kotlinx.coroutines.flow.Flow


interface PhotosRepository {

    suspend fun getRemotePhotos() : Result<List<Photo>>

    suspend fun getLocalPhotos() : Flow<List<Photo>>

    suspend fun savePhotos(photos : List<Photo>)

}