package com.example.finnmarketplace.ui.advertisement

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finnmarketplace.utils.Constant
import com.example.finnmarketplace.R
import com.example.finnmarketplace.data.local.AdvertisementEntity
import com.example.finnmarketplace.databinding.ItemAdvertisementBinding
import com.example.finnmarketplace.utils.Utils

class AdvertisementAdaptor(private val listener: AdvertisementItemListener) :
    RecyclerView.Adapter<AdvertisementViewHolder>() {

    interface AdvertisementItemListener {
        fun onClickedAdvertisement(advertisementId: String)
        fun onClickedBookmarked(advertisement: AdvertisementEntity)
    }

    private val items = ArrayList<AdvertisementEntity>()

    fun setItems(items: ArrayList<AdvertisementEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = items[position].id.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val binding: ItemAdvertisementBinding = ItemAdvertisementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AdvertisementViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) =
        holder.bind(items[position])
}

class AdvertisementViewHolder(
    private val itemBinding: ItemAdvertisementBinding,
    private val listener: AdvertisementAdaptor.AdvertisementItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var advertisement: AdvertisementEntity

    init {
        itemBinding.root.setOnClickListener(this)
        itemBinding.cvAdvertisement.setOnClickListener(this)
        itemBinding.bAddBookmark.setOnClickListener {
            listener.onClickedBookmarked(advertisement)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: AdvertisementEntity) {
        this.advertisement = item
        itemBinding.tvAddPrice.text = Utils.getCurrencyFormattedValue(item.price?.value.toString())
        itemBinding.tvAddLocation.text = item.location
        itemBinding.tvAddDescription.text = item.description

        val bookmarkImage = if (item.isBookmarked == 1)
            android.R.drawable.star_on
        else
            android.R.drawable.star_off
        itemBinding.bAddBookmark.setBackgroundResource(bookmarkImage)

        if (item.image != null) {
            val imageUrl = Constant.ServiceEndpoints.IMAGE_BASE_URL + item.image.url
            Glide.with(itemBinding.root)
                .load(imageUrl)
                .placeholder(R.drawable.finnlogo)
                .error(R.drawable.finnlogo)
                .into(itemBinding.ivAdd)
        }
    }

    override fun onClick(v: View?) {
        listener.onClickedAdvertisement(advertisement.id)
    }
}
