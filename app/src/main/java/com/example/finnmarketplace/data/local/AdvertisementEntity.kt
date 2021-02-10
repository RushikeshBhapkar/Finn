package com.example.finnmarketplace.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * AdvertisementEntity corresponds to table in the database and fields are columns
 */
@Entity(tableName = "advertisements")
data class AdvertisementEntity(
    @PrimaryKey
    var id: String,
    /**
     * Column name
     */
    @ColumnInfo(name = "ad-type")
    var adType: String?,
    /**
     * Nested fields can be referenced directly in the SQL queries.
     * @Embedded annotation to represent an object that you'd like to
     * decompose into its subfields within a table.
     */
    @Embedded
    val image: Image?,
    @Embedded
    val price: Price?,
    var description: String?,
    var location: String?,
    var type: String?,
    var score: String?,
    var version: String?,
    var isBookmarked: Int?
)



