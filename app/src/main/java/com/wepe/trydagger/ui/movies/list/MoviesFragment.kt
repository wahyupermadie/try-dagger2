package com.wepe.trydagger.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.databinding.FragmentMoviesBinding

class MoviesFragment : BaseFragment(){
    private lateinit var binding : FragmentMoviesBinding
    override fun getViewModel(): BaseViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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