package com.miniapp.moviesearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.core.presentation.OnItemClickListener
import com.miniapp.core.presentation.util.loadPosterImage
import com.miniapp.moviesearch.databinding.LayoutItemMovieSearchBinding

class MovieSearchAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MovieItemDomainModel, MovieSearchAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: LayoutItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemMovieSearchBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }

        fun bind(movie: MovieItemDomainModel, listener: OnItemClickListener) {
            binding.ivMovieSearchThumbnail.loadPosterImage(movie.posterPath)
            binding.tvMovieSearchTitle.text = movie.title
            binding.tvMovieSearchReleaseDate.text = movie.releaseDate
            binding.tvMovieRating.text = movie.voteAverage.toString()
            binding.pbMovieRating.progress = (movie.voteAverage * 10).toInt()
            binding.cardMovieSearchItem.setOnClickListener {
                listener.onClick(movie.id)
            }

            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieItemDomainModel>() {
        override fun areItemsTheSame(
            oldItem: MovieItemDomainModel, newItem: MovieItemDomainModel
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MovieItemDomainModel, newItem: MovieItemDomainModel
        ): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], listener)
    }
}