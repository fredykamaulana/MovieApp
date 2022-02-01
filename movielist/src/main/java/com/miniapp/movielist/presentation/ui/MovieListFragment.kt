package com.miniapp.movielist.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.presentation.base.BaseFragment
import com.miniapp.movielist.R
import com.miniapp.movielist.databinding.FragmentMovieListBinding
import com.miniapp.movielist.di.injectKoinModules
import com.miniapp.movielist.presentation.adapter.MovieCategoryAdapter
import com.miniapp.core.presentation.OnItemClickListener
import com.miniapp.movielist.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieListFragment : BaseFragment<FragmentMovieListBinding>(), OnItemClickListener {

    private val vm: MovieListViewModel by viewModel()
    private val movieCategoryAdapter: MovieCategoryAdapter by lazy { MovieCategoryAdapter(this) }

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_list

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
        binding.rvWatchlist.adapter = movieCategoryAdapter
        binding.swipeRefreshLayout.apply {
            setOnRefreshListener { isRefreshing = false }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObserver() {
        vm.popularMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getPopularMovies() }
                }
                else -> {}
            }
        })

        vm.nowPlayingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getNowPlayingMovies() }
                }
                else -> {}
            }
        })

        vm.topRatedMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getTopRatedMovies() }
                }
                else -> {}
            }
        })

        vm.upcomingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getUpcomingMovies() }
                }
                else -> {}
            }
        })

        vm.getAllMovieList.observe(this, {
            when (it) {
                is ResourceState.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    movieCategoryAdapter.submitList(vm.groupByCategory(it.data).toMutableList())
                    movieCategoryAdapter.notifyDataSetChanged()
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) { vm.getAllMovieList() }
                }
                else -> {}
            }
        })
    }

    override fun onClick(movieId: Int) {
        val uri = Uri.parse("miniApp://movieDetail/$movieId")
        findNavController().navigate(uri)
    }

}