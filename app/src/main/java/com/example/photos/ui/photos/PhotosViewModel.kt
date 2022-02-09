package com.example.photos.ui.photos

import androidx.lifecycle.*
import androidx.paging.*
import com.example.data.paging.PhotosDataSource
import com.example.data.paging.PhotosDataSourceFactory
import com.example.domain.common.Result
import com.example.domain.entities.Photo
import com.example.domain.usecases.GetLocalPhotosUseCase
import com.example.domain.usecases.GetRemotePhotosUseCase
import com.example.domain.usecases.SavePhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

class PhotosViewModel(
    getCashedPhotosUseCase: GetLocalPhotosUseCase,
    getPhotosUseCase: GetRemotePhotosUseCase,
    savePhotosUseCase: SavePhotosUseCase
    ) : ViewModel() {

    private val photosDataSourceFactory: PhotosDataSourceFactory =
        PhotosDataSourceFactory(getCashedPhotosUseCase,getPhotosUseCase, savePhotosUseCase ,viewModelScope)
    private val progressLoadStatus: LiveData<Result<List<Photo>>>
    private var photos: LiveData<PagedList<Photo>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()

        photos = LivePagedListBuilder(photosDataSourceFactory, pagedListConfig)
            .build()

        progressLoadStatus = Transformations.switchMap(photosDataSourceFactory.liveData,
            PhotosDataSource::getProgressLiveStatus)
    }

    fun getPhotos() = photos

    fun getProgressStatus() = progressLoadStatus

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}