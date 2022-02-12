package com.miniapp.movielist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.core.presentation.OnItemClickListener
import com.miniapp.core.presentation.util.loadPosterImage
import com.miniapp.movielist.databinding.LayoutItemMovieListBinding

class MovieListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MovieItemDomainModel, MovieListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: LayoutItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemDomainModel, listener: OnItemClickListener) {
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieRating.text = movie.voteAverage.toString()
            binding.tvMovieReleaseDate.text = movie.releaseDate
            binding.imgMoviePoster.loadPosterImage(movie.posterPath)
            binding.pbMovieRating.progress = (movie.voteAverage  * 10).toInt()
            binding.cardMovieItem.setOnClickListener { listener.onClick(movie.id) }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemMovieListBinding.inflate(inflater)

                return ViewHolder(binding)
            }
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