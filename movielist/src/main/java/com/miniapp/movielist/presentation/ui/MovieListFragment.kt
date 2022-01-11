package com.miniapp.movielist.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.presentation.base.BaseFragment
import com.miniapp.movielist.R
import com.miniapp.movielist.databinding.FragmentMovieListBinding
import com.miniapp.movielist.di.injectKoinModules
import com.miniapp.movielist.presentation.adapter.MovieListAdapter
import com.miniapp.movielist.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    private val vm: MovieListViewModel by viewModel()
    private val adapterPopular: MovieListAdapter by lazy { MovieListAdapter() }
    private val adapterNowPlaying: MovieListAdapter by lazy { MovieListAdapter() }
    private val adapterTopRated: MovieListAdapter by lazy { MovieListAdapter() }
    private val adapterUpcoming: MovieListAdapter by lazy { MovieListAdapter() }

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_list

    override fun onAttach(context: Context) {
        injectKoinModules()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        setupView()
        setupObserver()
    }

    private fun setupView() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvWatchlist)
        binding.rvWatchlist.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.rvWatchlist.adapter = ConcatAdapter(adapterPopular,adapterNowPlaying,adapterTopRated,adapterUpcoming)
    }

    private fun setupObserver() {
        vm.popularMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                    adapterPopular.submitList(it.data)
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getPopularMovies() }
                }
            }
        })

        vm.nowPlayingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                    adapterNowPlaying.submitList(it.data)
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getNowPlayingMovies() }
                }
            }
        })

        vm.topRatedMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                    adapterTopRated.submitList(it.data)
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getTopRatedMovies() }
                }
            }
        })

        vm.upcomingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                    adapterUpcoming.submitList(it.data)
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getUpcomingMovies() }
                }
            }
        })
    }

}