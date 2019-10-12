package com.wepe.trydagger.ui.movies.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wepe.trydagger.MainApplication
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.databinding.FragmentMoviesBinding
import com.wepe.trydagger.ui.movies.viewmodel.MoviesViewModel
import javax.inject.Inject

class MoviesFragment : BaseFragment(){
    private lateinit var binding : FragmentMoviesBinding

    private lateinit var viewModel: MoviesViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    companion object {

        fun newInstance() : MoviesFragment {
            return MoviesFragment()
        }
    }
    override fun onAttach(context: Context) {
        (activity?.application as MainApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMovies(1)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            Log.d("DATA_GUE","DATA "+it)
        })
    }
}