package com.example.finnmarketplace.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * ApplicationDatabase
 */
@Database(entities = [AdvertisementEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getAdvertisementDao(): AdvertisementDao

    companion object {
        @Volatile
        private var instance: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, ApplicationDatabase::class.java, "advertisements")
                .fallbackToDestructiveMigration()
                .build()
    }
}
