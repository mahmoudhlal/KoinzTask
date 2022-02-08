package com.example.domain.usecases

import com.example.domain.entities.Photo
import com.example.domain.repositories.PhotosRepository

class SavePhotosUseCase (private val photosRepository: PhotosRepository) {
    suspend operator fun invoke(photos : List<Photo>) = photosRepository.savePhotos(photos)
}