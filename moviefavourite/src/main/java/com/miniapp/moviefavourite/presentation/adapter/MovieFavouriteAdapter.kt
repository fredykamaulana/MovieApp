package com.miniapp.moviefavourite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.core.presentation.OnItemClickListener
import com.miniapp.core.presentation.util.loadPosterImage
import com.miniapp.moviefavourite.databinding.LayoutItemMovieFavouriteBinding

class MovieFavouriteAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MovieDetailDomainModel, MovieFavouriteAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: LayoutItemMovieFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemMovieFavouriteBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }

        fun bind(detail: MovieDetailDomainModel, listener: OnItemClickListener) {
            binding.imgMoviePoster.loadPosterImage(detail.posterPath)
            binding.tvMovieTitle.text = detail.title
            binding.tvMovieReleaseDate.text = detail.releaseDate
            binding.tvMovieRating.text = detail.voteAverage.toString()
            binding.pbMovieRating.progress = detail.voteAverage.toInt().times(10)
            binding.cardMovieItem.setOnClickListener { listener.onClick(detail.id) }

            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieDetailDomainModel>() {
        override fun areItemsTheSame(
            oldItem: MovieDetailDomainModel, newItem: MovieDetailDomainModel
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovieDetailDomainModel, newItem: MovieDetailDomainModel
        ): Boolean = oldItem.id == newItem.id

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }
}