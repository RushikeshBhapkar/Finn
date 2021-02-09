package com.example.finnmarketplace.di

import android.content.Context
import com.example.finnmarketplace.utils.Constant
import com.example.finnmarketplace.data.local.AdvertisementDao
import com.example.finnmarketplace.data.local.ApplicationDatabase
import com.example.finnmarketplace.data.remote.AdvertisementService
import com.example.finnmarketplace.data.remote.RemoteDataSource
import com.example.finnmarketplace.data.repository.AdvertisementRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.ServiceEndpoints.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAdvertisementService(retrofit: Retrofit): AdvertisementService =
        retrofit.create(AdvertisementService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(advertisementService: AdvertisementService) =
        RemoteDataSource(advertisementService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        ApplicationDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideAdvertisementDao(db: ApplicationDatabase) = db.getAdvertisementDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: AdvertisementDao
    ) = AdvertisementRepository(remoteDataSource, localDataSource)
}
