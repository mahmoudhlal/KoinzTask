package com.example.data.mappers

import com.example.data.entities.PhotoEntity
import com.example.domain.entities.Photo

class PhotoEntityMapper {
    fun toPhotoEntity(photo: Photo): PhotoEntity {
        return PhotoEntity(
            id = photo.id,
            secret = photo.secret,
            server = photo.server,
            farm = photo.farm,
            imageUrl = photo.imgUrl
        )
    }

    fun toPhoto(photoEntity: PhotoEntity): Photo {
        return Photo(
            photoEntity.id,
            photoEntity.secret,
            photoEntity.server,
            photoEntity.farm,
            photoEntity.imageUrl,
        )
    }
}