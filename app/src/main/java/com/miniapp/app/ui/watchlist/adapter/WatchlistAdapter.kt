package com.miniapp.app.ui.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miniapp.app.databinding.LayoutItemWatchListBinding
import com.miniapp.domain.watchlist.GetWatchlist

class WatchlistAdapter() :
    ListAdapter<GetWatchlist.DataItem, WatchlistAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ViewHolder(private val binding: LayoutItemWatchListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemWatchListBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(data: GetWatchlist.DataItem) {
            binding.tvInstanceCode.text = data.coinInfo?.name
            binding.tvInstanceName.text = data.coinInfo?.fullName
            binding.tvInstancePrice.text = data.display?.usd?.price
            binding.tvInstanceRating.text = data.coinInfo?.rating?.weiss?.rating
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<GetWatchlist.DataItem>() {
        override fun areItemsTheSame(
            oldItem: GetWatchlist.DataItem, newItem: GetWatchlist.DataItem
        ): Boolean = oldItem.coinInfo?.id == newItem.coinInfo?.id

        override fun areContentsTheSame(
            oldItem: GetWatchlist.DataItem, newItem: GetWatchlist.DataItem
        ): Boolean = oldItem == newItem
    }
}