package com.miniapp.movielist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.core.presentation.util.loadImage
import com.miniapp.movielist.databinding.LayoutItemMovieListBinding

class MovieListAdapter : ListAdapter<MovieItemDomainModel, MovieListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: LayoutItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemMovieListBinding.inflate(inflater)

                return ViewHolder(binding)
            }
        }

        fun bind(movie: MovieItemDomainModel){
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieRating.text = movie.voteAverage.toString()
            binding.tvMovieReleaseDate.text = movie.releaseDate
            binding.imgMoviePoster.loadImage(movie.posterPath)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieItemDomainModel>() {
        override fun areItemsTheSame(oldItem: MovieItemDomainModel, newItem: MovieItemDomainModel
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieItemDomainModel, newItem: MovieItemDomainModel
        ): Boolean = oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}