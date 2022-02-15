package com.miniapp.moviefavourite.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.presentation.OnItemClickListener
import com.miniapp.core.presentation.base.BaseFragment
import com.miniapp.moviefavourite.R
import com.miniapp.moviefavourite.databinding.FragmentMovieFavouriteBinding
import com.miniapp.moviefavourite.di.injectKoinModules
import com.miniapp.moviefavourite.presentation.adapter.MovieFavouriteAdapter
import com.miniapp.moviefavourite.presentation.viewmodel.MovieFavouriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavouriteFragment : BaseFragment<FragmentMovieFavouriteBinding>(), OnItemClickListener {

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_favourite

    private val vm: MovieFavouriteViewModel by viewModel()
    private var adapter: MovieFavouriteAdapter? = null

    override fun onAttach(context: Context) {
        injectKoinModules()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        setupObserver()
        setupView()
    }

    private fun setupView() {
        adapter = MovieFavouriteAdapter(this)
        binding.rvMovieFavourite.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObserver() {
        vm.getAllMovieFavourite.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceState.Loading -> { binding.layoutLoading.root.visibility = View.VISIBLE }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE

                    if (it.data?.isNotEmpty() == true) {
                        binding.tvMovieFavouriteTitle.visibility = View.VISIBLE
                        binding.layoutEmptyList.root.visibility = View.GONE
                    } else {
                        binding.tvMovieFavouriteTitle.visibility = View.GONE
                        binding.layoutEmptyList.root.visibility = View.VISIBLE
                    }

                    adapter?.submitList(it.data)
                    adapter?.notifyDataSetChanged()
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    binding.layoutEmptyList.root.visibility = View.VISIBLE
                    showSnackBar(binding.root, it.message.toString()) { vm.getAllMovieFavourite() }
                }
            }
        }
    }

    override fun onClick(movieId: Int) {
        val uri = Uri.parse("miniApp://movieDetail/$movieId")
        findNavController().navigate(uri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}