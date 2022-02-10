package com.example.domain.repositories

import com.example.domain.common.Result
import com.example.domain.entities.Photo


interface PhotosRepository {

    suspend fun getRemotePhotos(page:Int) : Result<List<Photo>>

    suspend fun getLocalPhotos(page:Int) : List<Photo>

    suspend fun savePhotos(photos : List<Photo>)

}