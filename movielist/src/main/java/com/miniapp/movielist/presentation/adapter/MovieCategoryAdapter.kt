package com.miniapp.movielist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miniapp.core.domain.movielistmodel.MovieItemDomainModel
import com.miniapp.movielist.databinding.LayoutItemMovieCategoryBinding

class MovieCategoryAdapter : ListAdapter<List<MovieItemDomainModel>, MovieCategoryAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: LayoutItemMovieCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        private val movieListAdapter by lazy { MovieListAdapter() }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemMovieCategoryBinding.inflate(inflater)
                return ViewHolder(binding)
            }
        }

        fun bind(movieList: List<MovieItemDomainModel>){
            val category = when(movieList[0].category){
                "popular" -> "Popular"
                "now_playing" -> "Now Playing"
                "top_rated" -> "Top Rated"
                "upcoming" -> "Upcoming"
                else  -> ""
            }
            binding.tvTitleCategory.text = category
            movieListAdapter.submitList(movieList)
            binding.rvMovieCategory.adapter = movieListAdapter
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<List<MovieItemDomainModel>>() {
        override fun areItemsTheSame(
            oldItem: List<MovieItemDomainModel>, newItem: List<MovieItemDomainModel>
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: List<MovieItemDomainModel>, newItem: List<MovieItemDomainModel>
        ): Boolean = oldItem[0].category == newItem[0].category

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.create(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}