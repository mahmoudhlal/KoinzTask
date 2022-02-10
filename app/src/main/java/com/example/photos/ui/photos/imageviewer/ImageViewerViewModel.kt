package com.example.photos.ui.photos.imageviewer

import androidx.lifecycle.*

class ImageViewerViewModel() : ViewModel() {
    private var photo : MutableLiveData<String> = MutableLiveData()
    private var _photo : LiveData<String> = photo

    fun getPhoto() = _photo

    fun setUrl(photoUrl : String){
        photo.value = photoUrl
    }
    class ImageViewerViewModelFactory(private val photoUrl : String) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ImageViewerViewModel() as T
        }
    }
}