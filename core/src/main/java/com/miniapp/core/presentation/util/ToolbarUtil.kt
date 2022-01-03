package com.miniapp.core.presentation.util

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar,
    titleString: String,
    titleTextView: TextView? = null,
    backClickHandler: (() -> Unit)? = null
) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayShowTitleEnabled(titleTextView==null)
    supportActionBar?.title = titleString
    titleTextView?.text = titleString
    toolbar.setNavigationOnClickListener {
        backClickHandler?.run { this() } ?: onBackPressed()
    }
}