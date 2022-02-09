package com.example.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.domain.entities.Photo
import com.example.domain.usecases.GetLocalPhotosUseCase
import com.example.domain.usecases.GetRemotePhotosUseCase
import com.example.domain.usecases.SavePhotosUseCase
import kotlinx.coroutines.CoroutineScope

class PhotosDataSourceFactory(private val getCachedPhotosUseCase: GetLocalPhotosUseCase,
                              private val getPhotosUseCase: GetRemotePhotosUseCase,
                              private val savePhotosUseCase: SavePhotosUseCase,
                              private val scope: CoroutineScope
) :  DataSource.Factory<Int, Photo>(){

    val liveData = MutableLiveData<PhotosDataSource>()
    lateinit var photosDataSource: PhotosDataSource

    override fun create(): DataSource<Int, Photo> {
        photosDataSource = PhotosDataSource(getCachedPhotosUseCase, getPhotosUseCase, savePhotosUseCase,scope)
        liveData.postValue(photosDataSource)
        return photosDataSource
    }
}