package com.example.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.domain.common.Result
import com.example.domain.entities.Photo
import com.example.domain.usecases.GetLocalPhotosUseCase
import com.example.domain.usecases.GetRemotePhotosUseCase
import com.example.domain.usecases.SavePhotosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PhotosDataSource(private val getCachedPhotosUseCase: GetLocalPhotosUseCase,
                       private val getPhotosUseCase: GetRemotePhotosUseCase,
                       private val savePhotosUseCase: SavePhotosUseCase,
                       private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Photo>()  {

    private val progressLiveStatus = MutableLiveData<Result<List<Photo>>>()

    fun getProgressLiveStatus() = progressLiveStatus


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        scope.launch {
            val cachedPhotos = getCachedPhotosUseCase.invoke(params.key)
            if (cachedPhotos.isEmpty()) {
                progressLiveStatus.postValue(Result.Loading(listOf()))
                when (val photos = getPhotosUseCase.invoke(params.key)) {
                    is Result.Success -> {
                        savePhotosUseCase.invoke(photos.data)
                        progressLiveStatus.postValue(photos)
                        callback.onResult(photos.data, params.key + 1)
                    }
                    else -> progressLiveStatus.postValue(photos)
                }
            }else{
                callback.onResult(cachedPhotos, params.key + 1)
                progressLiveStatus.postValue(Result.Success(cachedPhotos))
            }
        }
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        scope.launch {
            val cachedPhotos = getCachedPhotosUseCase.invoke(1)
            if (cachedPhotos.isEmpty()) {
                when(val photos = getPhotosUseCase.invoke(1)){
                    is Result.Success -> {
                        savePhotosUseCase.invoke(photos.data)
                        progressLiveStatus.postValue(photos)
                        callback.onResult(photos.data, null, 2)
                    }
                    is Result.Error -> progressLiveStatus.postValue(photos)
                    is Result.Loading -> progressLiveStatus.postValue(photos)
                }
            }else{
                callback.onResult(cachedPhotos, null, 2)
                progressLiveStatus.postValue(Result.Success(cachedPhotos))
            }
        }
    }




}