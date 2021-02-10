package com.example.finnmarketplace.ui.advertisement

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.finnmarketplace.data.local.AdvertisementEntity
import com.example.finnmarketplace.data.repository.AdvertisementRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * ViewModel for Advertisement fragment
 *
 * @ViewModelInject
 */
class AdvertisementViewModel @ViewModelInject constructor(
        private val repository: AdvertisementRepository
) : ViewModel() {

    val advertisements = repository.getAdvertisements()

    // TODO : Handle offline scenario
    //val advertisements = repository.getBookmarkedAdvertisements()

    fun bookmarkAdvertisement(adId: String) {
        repository.bookmarkAdvertisement(adId)
    }

    fun unBookmarkAdvertisement(adId: String) {
        repository.unBookmarkAdvertisement(adId)
    }
}
