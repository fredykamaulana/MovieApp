package com.miniapp.moviesearch.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.miniapp.core.data.source.remote.utils.RemoteResult
import com.miniapp.core.presentation.OnItemClickListener
import com.miniapp.core.presentation.base.BaseFragment
import com.miniapp.moviesearch.R
import com.miniapp.moviesearch.databinding.FragmentMovieSearchBinding
import com.miniapp.moviesearch.di.injectKoinModules
import com.miniapp.moviesearch.presentation.adapter.MovieSearchAdapter
import com.miniapp.moviesearch.presentation.viewmodel.MovieSearchViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ObsoleteCoroutinesApi
class MovieSearchFragment : BaseFragment<FragmentMovieSearchBinding>(), OnItemClickListener {

    private val vm: MovieSearchViewModel by viewModel()
    private val adapter: MovieSearchAdapter by lazy { MovieSearchAdapter(this) }

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_search

    override fun onAttach(context: Context) {
        injectKoinModules()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        observeMovieSearch()
        setupView()
    }

    private fun setupView() {
        binding.tvSearchMovie.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.isNotEmpty() == true || query?.isNotBlank() == true) {
                        vm.search(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true || newText?.isNotBlank() == true) {
                        vm.search(newText)
                    }
                    return false
                }
            }
        )

        binding.btnSearch.setOnClickListener {
            if (binding.tvSearchMovie.query.toString().isNotEmpty() ||
                binding.tvSearchMovie.query.toString().isNotBlank()
            ) {
                vm.search(binding.tvSearchMovie.query.toString())
            }
        }

        binding.rvSearchMovie.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeMovieSearch() {
        vm.searchResult.observe(viewLifecycleOwner) {
            when (it) {
                is RemoteResult.Empty -> {}
                is RemoteResult.Success -> {
                    adapter.submitList(it.data)
                    adapter.notifyDataSetChanged()
                }
                is RemoteResult.Error -> {
                    showSnackBar(binding.root, "Terjadi kesalahan saat mengambil data")
                }
            }
        }
    }

    override fun onClick(movieId: Int) {
        val uri = Uri.parse("miniApp://movieDetail/$movieId")
        findNavController().navigate(uri)
    }

}