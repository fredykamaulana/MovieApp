package com.miniapp.app.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.miniapp.app.R
import com.miniapp.app.databinding.ActivityMainBinding
import com.miniapp.core.presentation.base.BaseActivity
import com.miniapp.core.presentation.util.setupToolbar

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        Navigation.findNavController(this, R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupToolbar(binding.toolbar,"")
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}