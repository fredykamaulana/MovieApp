package com.miniapp.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.miniapp.app.framework.injection.injectKoinModules

abstract class BaseFragment(@LayoutRes private val contentLayoutId: Int? = null) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        injectKoinModules()
        if (contentLayoutId != null) return inflater.inflate(contentLayoutId, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun showSnackBar(
        view: View,
        message: String,
        action: String = "RETRY",
        actionClick: () -> Unit = {}
    ) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(action) { actionClick() }
            .show()
    }
}