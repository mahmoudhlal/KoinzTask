package com.example.data.repositories.photos

import com.example.domain.common.Result
import com.example.domain.entities.Photo

interface PhotosRemoteDataSource {
    suspend fun getPhotos(page: Int): Result<List<Photo>>
}