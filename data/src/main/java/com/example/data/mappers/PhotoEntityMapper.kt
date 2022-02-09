package com.example.data.mappers

import com.example.data.entities.PhotoEntity
import com.example.domain.entities.Photo

class PhotoEntityMapper {
    fun toPhotoEntity(photo: List<Photo>): List<PhotoEntity> {
        return photo.map {
            PhotoEntity(
                id = it.id,
                secret = it.secret,
                server = it.server,
                farm = it.farm,
                title = it.title,
                imageUrl = it.imgUrl
            )
        }
    }

    fun toPhoto(photoEntity: PhotoEntity): Photo {
        return Photo(
            photoEntity.id,
            photoEntity.secret,
            photoEntity.server,
            photoEntity.farm,
            photoEntity.title,
            photoEntity.imageUrl,
        )
    }
}