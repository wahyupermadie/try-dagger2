package com.wepe.trydagger.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.wepe.trydagger.extention.showSnackbar

abstract class BaseFragment : Fragment(){

    abstract fun getViewModel() : BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver(getViewModel())
    }

    private fun setupObserver(viewModel: BaseViewModel) {
        viewModel.errorHandler.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                showSnackbar(
                    it.message ?: "Terjadi kesalahan pada sistem",
                Snackbar.LENGTH_LONG)
            }
        })
    }
}