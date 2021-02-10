package com.example.finnmarketplace.data.remote

import javax.inject.Inject

/**
 * Remote data source which will be responsible to fetch the data from remote source
 *
 * @param advertisementService: AdvertisementService, Interface for retrofit
 *
 * @Inject : Inject the dependency from module
 */
class RemoteDataSource @Inject constructor(private val advertisementService: AdvertisementService) :
        BaseDataSource() {
    suspend fun getAdvertisements() = getResult { advertisementService.getAllAdvertisements() }
}
