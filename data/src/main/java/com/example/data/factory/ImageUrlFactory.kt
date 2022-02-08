package com.example.data.factory

import com.example.data.api.ApiPhoto

object ImageUrlFactory {
    fun generateImgUrl(photo : ApiPhoto) =
        "http://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
}