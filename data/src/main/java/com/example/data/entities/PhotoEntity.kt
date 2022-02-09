package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey
    val id: String,
    val secret:String,
    val server:String,
    val farm:String,
    val title:String,
    val imageUrl: String
)