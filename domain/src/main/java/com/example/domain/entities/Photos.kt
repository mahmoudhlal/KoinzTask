package com.example.domain.entities

data class Photos(
    val page: Int,
    val pages: String,
    val perpage: String,
    val total: String,
    val photo: List<Photo>
)