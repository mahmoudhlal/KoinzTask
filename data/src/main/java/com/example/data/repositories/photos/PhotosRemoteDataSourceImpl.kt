package com.example.data.repositories.photos

import com.example.data.api.PhotosApi
import com.example.data.mappers.PhotoApiResponseMapper
import com.example.domain.entities.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.domain.common.Result

class PhotosRemoteDataSourceImpl(
    private val service: PhotosApi,
    private val mapper: PhotoApiResponseMapper
    ) : PhotosRemoteDataSource {
    override suspend fun getPhotos(page: Int): Result<List<Photo>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getPhotos(page)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toPhotoList(response.body()!!))
                } else {
                    return@withContext Result.Error(response.message())
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e.message ?: "Nothing")
            }
        }


}