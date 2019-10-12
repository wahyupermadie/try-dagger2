package com.wepe.trydagger.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wepe.trydagger.di.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment(){

    abstract fun getViewModel() : BaseViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObserver(getViewModel())
    }

    private fun setupObserver(viewModel: BaseViewModel) {

    }
}