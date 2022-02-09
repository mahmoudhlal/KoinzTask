package com.example.data.api

import com.squareup.moshi.Json

class PhotosApiResponse(val photos: ApiPhotos , val stat : String)

data class ApiPhotos(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "pages")
    val pages: String,
    @field:Json(name = "perpage")
    val perpage: String,
    @field:Json(name = "total")
    val total: String,
    @field:Json(name = "photo")
    val photo: List<ApiPhoto>
)

data class ApiPhoto(
    val id:String,
    val secret:String,
    val server:String,
    val title:String,
    val farm:String
)


