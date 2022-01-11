package com.miniapp.core.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.miniapp.core.di.injectKoinModules

abstract class BaseFragment<out B : ViewDataBinding> : Fragment() {

    val binding: B
        get() = mViewDataBinding!!

    private var mViewDataBinding: B? = null

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    override fun onAttach(context: Context) {
        injectKoinModules()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectKoinModules()
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        with(mViewDataBinding) {
            this?.lifecycleOwner = viewLifecycleOwner
            this?.executePendingBindings()
        }
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        mViewDataBinding = null
    }
}