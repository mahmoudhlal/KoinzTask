package com.example.domain.usecases

import com.example.domain.repositories.PhotosRepository

class GetRemotePhotosUseCase(private val photosRepository: PhotosRepository) {
    suspend operator fun invoke() = photosRepository.getRemotePhotos()
}