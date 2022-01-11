package com.miniapp.core.presentation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.miniapp.core.BuildConfig

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar,
    titleString: String,
    titleTextView: TextView? = null,
    backClickHandler: (() -> Unit)? = null
) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayShowTitleEnabled(titleTextView == null)
    supportActionBar?.title = titleString
    titleTextView?.text = titleString
    toolbar.setNavigationOnClickListener {
        backClickHandler?.run { this() } ?: onBackPressed()
    }
}

fun ImageView.loadImage(posterPath: String) {
    Glide.with(context)
        .load("${BuildConfig.IMG_URL}${posterPath}")
        .centerCrop()
        .into(this)
}