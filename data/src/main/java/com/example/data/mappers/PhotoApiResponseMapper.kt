package com.example.data.mappers

import com.example.data.api.PhotosApiResponse
import com.example.data.factory.ImageUrlFactory
import com.example.domain.entities.Photo

class PhotoApiResponseMapper {
    fun toPhotoList(response: PhotosApiResponse): List<Photo> {
        return response.photos.photo.map {
            Photo(
                it.id,
                it.secret,
                it.server,
                it.farm,
                it.title,
                response.photos.page.toString(),
                ImageUrlFactory.generateImgUrl(it)
            )
        }
    }
}