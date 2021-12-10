package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bondidos.wotstatisticbybondidos.databinding.*
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_BANNER
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_BANNER_WITHOUT_IMAGE
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_CARD_ACHIEVE
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_CARD_WITH_TEXT
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_CARD_WITH_IMAGE
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import java.lang.IllegalArgumentException

class DataAdapter : RecyclerView.Adapter<DataAdapterViewHolder>() {

    private val adapterData = mutableListOf<MultiViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {

        val binding = when (viewType) {
            TYPE_BANNER -> BannerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TYPE_CARD_WITH_IMAGE -> CardWithImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TYPE_CARD_WITH_TEXT -> CardWithTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TYPE_CARD_ACHIEVE -> AchieveItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TYPE_BANNER_WITHOUT_IMAGE -> BannerWithoutImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            else -> throw IllegalArgumentException("Invalid type")
        }

        // full width if banner
        binding.root.layoutParams.apply {
            if ((viewType == TYPE_BANNER || viewType == TYPE_BANNER_WITHOUT_IMAGE) &&
                this is StaggeredGridLayoutManager.LayoutParams
            ) {
                this.isFullSpan = true
            }
        }
        return DataAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is MultiViewModel.Banner -> TYPE_BANNER
            is MultiViewModel.CardWithText -> TYPE_CARD_WITH_TEXT
            is MultiViewModel.CardWithImage -> TYPE_CARD_WITH_IMAGE
            is MultiViewModel.AchieveCard -> TYPE_CARD_ACHIEVE
            is MultiViewModel.BannerWithoutImage -> TYPE_BANNER_WITHOUT_IMAGE
        }
    }

    fun setData(data: List<MultiViewModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }
}
