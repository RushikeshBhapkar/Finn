package com.example.finnmarketplace.data.local

/**
 * Image data class, This will be used to decompose object into its sub-fields within a table.
 */
data class Image(
        val imageId: Int,
        val url: String,
        val height: Int,
        val width: Int,
        val scalable: Boolean
)
