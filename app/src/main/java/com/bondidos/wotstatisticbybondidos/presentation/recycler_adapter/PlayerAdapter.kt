package com.bondidos.wotstatisticbybondidos.presentation.recycler_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.wotstatisticbybondidos.domain.entityes.User

class PlayerAdapter () : ListAdapter<User, PlayerHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class PlayerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

}

class NoteDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

}