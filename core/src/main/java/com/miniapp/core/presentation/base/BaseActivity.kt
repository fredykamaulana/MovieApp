package com.miniapp.core.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.miniapp.core.di.injectKoinModules

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        injectKoinModules()
    }

    fun showSnackBar(
        view: View,
        message: String,
        action: String = "RETRY",
        actionClick: () -> Unit = {}
    ) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setAction(action) { actionClick() }
            .show()
    }
}