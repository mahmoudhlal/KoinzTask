package com.example.data.repositories.photos

import com.example.domain.common.Result
import com.example.domain.entities.Photo
import com.example.domain.repositories.PhotosRepository
import kotlinx.coroutines.flow.Flow

class PhotosRepositoryImpl(
    private val localDataSource: PhotosLocalDataSource,
    private val remoteDataSource: PhotosRemoteDataSource
): PhotosRepository{
    override suspend fun getRemotePhotos(page:Int): Result<List<Photo>> {
        return remoteDataSource.getPhotos(page)
    }

    override suspend fun getLocalPhotos(page:Int): List<Photo> {
        return localDataSource.getPhotos(page)
    }

    override suspend fun savePhotos(photos: List<Photo>) {
        localDataSource.savePhotos(photos)
    }
}