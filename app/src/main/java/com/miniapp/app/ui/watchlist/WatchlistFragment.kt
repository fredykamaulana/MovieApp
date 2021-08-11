package com.miniapp.app.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.miniapp.app.R
import com.miniapp.app.databinding.FragmentWatchlistBinding
import com.miniapp.app.ui.base.BaseFragment
import com.miniapp.app.ui.watchlist.adapter.WatchlistAdapter
import com.miniapp.data.util.RemoteResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment : BaseFragment() {
    private lateinit var binding: FragmentWatchlistBinding

    private val adapter: WatchlistAdapter by lazy { WatchlistAdapter() }
    private val vm: WatchListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupView()
        initData()
    }

    private fun setupObserver() {
        vm.getWatchlist.observe(viewLifecycleOwner, {
            when (it) {
                is RemoteResult.Loading -> { binding.groupLoading.visibility = View.VISIBLE }
                is RemoteResult.Success -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.groupLoading.visibility = View.GONE
                    adapter.submitList(it.data.data)
                }
                is RemoteResult.Error -> {
                    binding.groupLoading.visibility = View.GONE
                    showSnackBar(binding.root, it.errorMessage ?:""){ vm.getWatchlist() }
                }
                else -> { }
            }
        })
    }

    private fun setupView() {
        binding.run {
            rvWatchlist.addItemDecoration(
                DividerItemDecoration(requireContext(), LinearLayoutManager.HORIZONTAL)
            )
            rvWatchlist.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener { vm.getWatchlist() }
        }
    }

    private fun initData() {
        vm.getWatchlist()
    }
}