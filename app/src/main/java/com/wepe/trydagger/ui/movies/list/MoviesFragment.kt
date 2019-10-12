package com.wepe.trydagger.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.databinding.FragmentMoviesBinding
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel

class MoviesFragment : BaseFragment(){
    private lateinit var binding : FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    override fun getViewModel(): BaseViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}