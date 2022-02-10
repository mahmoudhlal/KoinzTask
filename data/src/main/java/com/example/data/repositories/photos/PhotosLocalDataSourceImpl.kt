package com.example.data.repositories.photos

import com.example.data.db.PhotoDao
import com.example.data.mappers.PhotoEntityMapper
import com.example.domain.entities.Photo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotosLocalDataSourceImpl(
    private val photoDao: PhotoDao,
    private val dispatcher: CoroutineDispatcher,
    private val photoEntityMapper: PhotoEntityMapper
    ) : PhotosLocalDataSource{
    override suspend fun savePhotos(photos: List<Photo>) = withContext(dispatcher){
        photoDao.savePhotos(photoEntityMapper.toPhotoEntity(photos))
    }

    override suspend fun getPhotos(page:Int): List<Photo> {
        val savedPhotosFlow = photoDao.getSavedPhotos(page)
        return savedPhotosFlow.map {
            photoEntityMapper.toPhoto(it)
        }
    }


}