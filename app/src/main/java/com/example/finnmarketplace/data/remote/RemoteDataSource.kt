package com.example.finnmarketplace.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val advertisementService: AdvertisementService) :
    BaseDataSource() {
    suspend fun getAdvertisements() = getResult { advertisementService.getAllAdvertisements() }
}
