package com.example.photos.ui.photos

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetLocalPhotosUseCase
import com.example.domain.usecases.GetRemotePhotosUseCase
import com.example.domain.usecases.SavePhotosUseCase

class PhotosViewModel(
    private val getPhotosUseCase: GetLocalPhotosUseCase,
    private val getCashedPhotosUseCase: GetRemotePhotosUseCase,
    private val savePhotosUseCase: SavePhotosUseCase
    ) : ViewModel() {


}