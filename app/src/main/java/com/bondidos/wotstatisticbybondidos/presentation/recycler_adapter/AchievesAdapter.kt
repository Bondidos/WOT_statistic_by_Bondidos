package com.bondidos.wotstatisticbybondidos.presentation.recycler_adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoaderFactory
import coil.load
import com.bondidos.wotstatisticbybondidos.R
import com.bondidos.wotstatisticbybondidos.databinding.AchieveItemBinding
import okhttp3.OkHttpClient
import java.net.URI

import javax.inject.Inject

class AchievesAdapter : ListAdapter<Any, AchievesAdapter.AchieveViewHolder>(AchieveDiffCallback()) {



    //todo DO REFACTOR
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchieveViewHolder {
        return AchieveViewHolder(
            AchieveItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AchieveViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
           /* received.text = item.received.toString()
            imageView.load(item.imageBig?.replace("http","https") ?: "")*/
        }
    }

    class AchieveDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return true
        }

    }

    class AchieveViewHolder(binding: AchieveItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val received = binding.received
        val imageView = binding.imageView
    }
}
