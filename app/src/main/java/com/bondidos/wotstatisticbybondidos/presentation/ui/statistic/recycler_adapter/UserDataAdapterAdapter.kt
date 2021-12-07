package com.bondidos.wotstatisticbybondidos.presentation.ui.statistic.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.ViewBinding
import coil.load
import com.bondidos.wotstatisticbybondidos.databinding.BannerItemBinding
import com.bondidos.wotstatisticbybondidos.databinding.CardWithImageBinding
import com.bondidos.wotstatisticbybondidos.databinding.CardWithTextBinding
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_BANNER
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_CARD_WITH_TEXT
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TYPE_CARD_WITH_IMAGE
import com.bondidos.wotstatisticbybondidos.domain.entityes.MultiViewModel
import java.lang.IllegalArgumentException

class UserDataAdapterAdapter: RecyclerView.Adapter<DataAdapterViewHolder>() {

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
            else -> throw IllegalArgumentException("Invalid type")
        }

        // full width if banner
        binding.root.layoutParams.apply {
            if (viewType == TYPE_BANNER && this is StaggeredGridLayoutManager .LayoutParams) {
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
        }
    }

    fun setData(data: List<MultiViewModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }
}


class DataAdapterViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    private fun bindBanner(item: MultiViewModel.Banner) {
        (binding as BannerItemBinding).apply {
            header.text = item.header
            item.image?.let {
                when(it){
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

    fun bind(dataModel: MultiViewModel) {
        when (dataModel) {
            is MultiViewModel.Banner -> bindBanner(dataModel)
            is MultiViewModel.CardWithImage -> bindCardWithImage(dataModel)
            is MultiViewModel.CardWithText -> bindCardWithText(dataModel)
        }
    }
}
