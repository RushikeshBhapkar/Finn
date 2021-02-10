package com.example.finnmarketplace.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * AdvertisementDao : Data Access Object
 *
 * This class provides object oriented access to underlying SQLite database,
 * So we don't have to write queries for all the CRUD operations
  */
@Dao
interface AdvertisementDao {
    /**
     * Get Advertisement
     */
    @Query("SELECT * FROM advertisements")
    fun getAdvertisements() : LiveData<List<AdvertisementEntity>>

    /**
     * Insert query
     * OnConflictStrategy.IGNORE : This will keep ignore if duplicate entry
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAdvertisements(advertisements : List<AdvertisementEntity>)

    /**
     * Delete Query
     */
    @Query("DELETE FROM advertisements")
    fun deleteAdvertisements()

    /**
     * Update advertisement by bookmark
     *
     */
    @Query("UPDATE advertisements SET isBookmarked = :bookmark WHERE id = :adId")
    fun bookmarkAdvertisement(adId: String, bookmark: Int)

    /**
     * Get Query
     * Get all bookmarked advertisement
     */
    @Query("SELECT * FROM advertisements WHERE isBookmarked= 1")
    fun getBookmarkedAdvertisements() : LiveData<List<AdvertisementEntity>>
}
