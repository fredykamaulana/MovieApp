package com.miniapp.moviedetail.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.miniapp.core.data.mapper.toSingleString
import com.miniapp.core.data.source.vo.ResourceState
import com.miniapp.core.domain.moviedetailmodel.MovieDetailDomainModel
import com.miniapp.core.presentation.base.BaseFragment
import com.miniapp.core.presentation.util.loadHDImage
import com.miniapp.moviedetail.R
import com.miniapp.moviedetail.databinding.FragmentMovieDetailBinding
import com.miniapp.moviedetail.di.injectKoinModules
import com.miniapp.moviedetail.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val movieId by lazy { args.movieId }
    private val vm: MovieDetailViewModel by viewModel { parametersOf(movieId)}


    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_detail

    override fun onAttach(context: Context) {
        injectKoinModules()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        vm.getMovieDetailById()
    }

    private fun setupObserver() {

        vm.movieDetail.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceState.Loading -> {
                    binding.layoutLoading.root.visibility = View.VISIBLE
                }
                is ResourceState.Success -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    setupView(it.data)
                }
                is ResourceState.Error -> {
                    binding.layoutLoading.root.visibility = View.GONE
                    showSnackBar(binding.root, it.message.toString()) {
                        vm.getMovieDetailById()
                    }
                }
                else -> {}
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(detail: MovieDetailDomainModel?) {
        binding.ivBackdrop.loadHDImage(detail?.backdropPath ?: "")
        binding.tvMovieTitle.text = detail?.originalTitle
        binding.tvCategory.text = detail?.status
        binding.tvMovieRating.text = detail?.voteAverage.toString()
        binding.pbMovieRating.progress = detail?.voteAverage?.toInt()?.times(10) ?: 0
        binding.tvMovieDescription.text = detail?.overview
        binding.tvGenres.text = detail?.genresName?.toSingleString()
        binding.tvStudio.text = detail?.productionCompaniesName?.toSingleString()
        binding.tvRelease.text = detail?.releaseDate
        binding.tvRevenue.text = "USD ${detail?.revenue}"
        binding.tvLanguage.text = detail?.spokenLanguagesName?.toSingleString() ?: ""
        binding.tvAudience.text = if (detail?.adult == true) "Adult" else "All Ages"
        binding.tvSites.text = detail?.homepage ?: ""

        if (detail?.favourite == true){
            binding.ivFavouriteFilled.visibility = View.VISIBLE
            binding.ivFavourite.visibility = View.GONE
        } else {
            binding.ivFavouriteFilled.visibility = View.GONE
            binding.ivFavourite.visibility = View.VISIBLE
        }

        binding.ivFavourite.setOnClickListener {
            val fav = detail?.favourite
            with(vm) {
                setMovieAsFavourite(detail?.id ?: 0, !fav!!)
            }
            it.visibility = View.GONE
            binding.ivFavouriteFilled.visibility = View.VISIBLE
        }

        binding.ivFavouriteFilled.setOnClickListener {
            val fav = detail?.favourite
            with(vm) {
                setMovieAsFavourite(detail?.id ?: 0, !fav!!)
            }
            it.visibility = View.GONE
            binding.ivFavourite.visibility = View.VISIBLE
        }
    }
}