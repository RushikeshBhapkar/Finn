package com.example.finnmarketplace.data.remote

import com.example.finnmarketplace.utils.Constant
import com.example.finnmarketplace.data.local.AdvertisementList
import retrofit2.Response
import retrofit2.http.GET

interface AdvertisementService {
    @GET(Constant.ServiceEndpoints.GET_ALL_ADVERTISEMENTS)
    suspend fun getAllAdvertisements() : Response<AdvertisementList>
}
