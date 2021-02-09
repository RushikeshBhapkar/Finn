package com.example.finnmarketplace.data.local

data class Image(
    val imageId: Int,
    val url: String,
    val height: Int,
    val width: Int,
    val scalable: Boolean
)
