package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.recycler_adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.bondidos.wotstatisticbybondidos.databinding.*
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel

class DataAdapterViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private fun bindBanner(item: MultiViewModel.Banner) {
        (binding as BannerItemBinding).apply {
            header.text = item.header
            item.image?.let {
                when (it) {
                    is Int -> imgBanner.setImageResource(it)
                    is String -> imgBanner.load(it.toString())
                }
            }
        }
    }

    private fun bindCardWithText(item: MultiViewModel.CardWithText) {
        (binding as CardWithTextBinding).apply {
            header.text = item.header
            text.text = item.text
        }
    }

    private fun bindCardWithImage(item: MultiViewModel.CardWithImage) {
        (binding as CardWithImageBinding).apply {
            header.text = item.header
            item.image?.let { imgBanner.setImageResource(it) }
        }
    }

    private fun bindCardWithAchieve(item: MultiViewModel.AchieveCard) {
        (binding as AchieveItemBinding).apply {
            imageView.load(item.image)
            received.text = item.scored
        }
    }

    private fun bindBannerWithoutImage(item: MultiViewModel.BannerWithoutImage) {
        (binding as BannerWithoutImageBinding).apply {
            header.text = item.header
        }
    }

    fun bind(dataModel: MultiViewModel) {
        when (dataModel) {
            is MultiViewModel.Banner -> bindBanner(dataModel)
            is MultiViewModel.BannerWithoutImage -> bindBannerWithoutImage(dataModel)
            is MultiViewModel.CardWithImage -> bindCardWithImage(dataModel)
            is MultiViewModel.CardWithText -> bindCardWithText(dataModel)
            is MultiViewModel.AchieveCard -> bindCardWithAchieve(dataModel)
        }
    }
}