package com.wepe.trydagger.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    abstract fun getViewModel() : BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver(getViewModel())
    }

    private fun setupObserver(viewModel: BaseViewModel) {

    }
}