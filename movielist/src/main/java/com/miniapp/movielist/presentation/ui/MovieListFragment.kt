package com.miniapp.movielist.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.presentation.base.BaseFragment
import com.miniapp.movielist.R
import com.miniapp.movielist.databinding.FragmentMovieListBinding
import com.miniapp.movielist.di.injectKoinModules
import com.miniapp.movielist.presentation.adapter.MovieCategoryAdapter
import com.miniapp.movielist.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    private val vm: MovieListViewModel by viewModel()
    private val movieCategoryAdapter: MovieCategoryAdapter by lazy { MovieCategoryAdapter() }

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
        binding.rvWatchlist.adapter = movieCategoryAdapter
        binding.swipeRefreshLayout.apply {
            setOnRefreshListener { isRefreshing = false }
        }
    }

    private fun setupObserver() {
        vm.popularMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getPopularMovies() }
                }
                else -> {}
            }
        })

        vm.nowPlayingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getNowPlayingMovies() }
                }
                else -> {}
            }
        })

        vm.topRatedMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getTopRatedMovies() }
                }
                else -> {}
            }
        })

        vm.upcomingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getUpcomingMovies() }
                }
                else -> {}
            }
        })

        vm.getAllMovieList.observe(this, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.groupLoading.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.groupLoading.visibility = View.GONE
                    movieCategoryAdapter.submitList(vm.groupByCategory(it.data).toMutableList())
                }
                is ResourceState.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getAllMovieList() }
                }
                else -> {}
            }
        })
    }

}