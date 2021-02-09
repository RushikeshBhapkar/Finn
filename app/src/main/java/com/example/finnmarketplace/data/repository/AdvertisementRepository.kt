package com.example.finnmarketplace.data.repository

import com.example.finnmarketplace.data.local.AdvertisementDao
import com.example.finnmarketplace.data.remote.RemoteDataSource
import com.example.finnmarketplace.utils.executeGetOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * AdvertisementRepository is bridge between ViewModel and other data sources(Local database,
 * Remote database). View model doesn't know from where data is coming, Also if data is old
 * repository will start updating it in background.
 * @param remoteDataSource: RemoteDataSource, Remote data source
 * @param localDataSource: AdvertisementDao, Local database
 */
class AdvertisementRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: AdvertisementDao
) {
    /**
     * Get Advertisement operation
     *
     * This method do three operations
     * 1. Fetch the advertisement from local Database, so that app can show stored
     *    advertisement into app before app updates the latest data from server
     * 2. Do network call to fetch advertisement
     * 3. Update fetched advertisement from network to local database
     */
    fun getAdvertisements() = executeGetOperation(
        databaseQuery = { localDataSource.getAdvertisements() },
        networkCall = { remoteDataSource.getAdvertisements() },
        saveCallResult = { localDataSource.saveAdvertisements(it.items) }
    )

    /**
     * Get all bookmarked advertisements
     *
     * This method do three operations
     * 1. Fetch the bookmarked advertisement from local Database
     * 2. Do network call to fetch advertisement
     * 3. Update fetched advertisement from network to local database
     */
    fun getBookmarkedAdvertisements() = executeGetOperation(
        databaseQuery = { localDataSource.getBookmarkedAdvertisements() },
        networkCall = { remoteDataSource.getAdvertisements() },
        saveCallResult = { localDataSource.saveAdvertisements(it.items) }
    )

    /**
     * Sqlite doesn't support boolean data type so keeping 1 (bookmark) and 0 (un-bookmark)
     *
     * @param bookmark: 1, bookmark advertisement in DB
     * @param adId: String, AdvertisementID in DB
     */
    fun bookmarkAdvertisement(adId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.bookmarkAdvertisement(adId = adId, bookmark = 1)
        }
    }

    /**
     * Sqlite doesn't support boolean data type so keeping 1 (bookmark) and 0 (un-bookmark)
     *
     * @param bookmark: 0, un-bookmark advertisement in DB
     * @param adId: String, AdvertisementID in DB
     */
    fun unBookmarkAdvertisement(adId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.bookmarkAdvertisement(adId = adId, bookmark = 0)
        }
    }
}
