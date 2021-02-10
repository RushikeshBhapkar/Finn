package com.example.finnmarketplace.ui.advertisement

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finnmarketplace.data.local.AdvertisementEntity
import com.example.finnmarketplace.databinding.AdvertisementFragmentBinding
import com.example.finnmarketplace.utils.Result
import com.example.finnmarketplace.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdvertisementFragment : Fragment(), AdvertisementAdaptor.AdvertisementItemListener {
    private var binding: AdvertisementFragmentBinding by autoCleared()
    private val viewModel: AdvertisementViewModel by viewModels()
    private lateinit var adapter: AdvertisementAdaptor

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = AdvertisementFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = AdvertisementAdaptor(this)
        adapter.setHasStableIds(true)
        binding.rvAdvertisement.layoutManager =
                GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.rvAdvertisement.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.advertisements.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Result.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Result.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedAdvertisement(advertisementId: String) {
        Toast.makeText(requireContext(), "Add id : $advertisementId", Toast.LENGTH_SHORT).show()
    }

    override fun onClickedBookmarked(advertisement: AdvertisementEntity) {

        if (advertisement.isBookmarked == 1) {
            viewModel.unBookmarkAdvertisement(advertisement.id)
            Toast.makeText(requireContext(), "Item ${advertisement.id} Unbookmarked", Toast.LENGTH_SHORT).show()

        } else {
            viewModel.bookmarkAdvertisement(advertisement.id)
            Toast.makeText(requireContext(), "Item ${advertisement.id} Bookmarked", Toast.LENGTH_SHORT).show()
        }
    }
}
