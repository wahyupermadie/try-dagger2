package com.wepe.trydagger.ui.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.databinding.FragmentFavMoviesBinding
import com.wepe.trydagger.ui.movies.adapter.MoviesAdapter
import com.wepe.trydagger.ui.movies.detail.DetailMovieActivity
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class MoviesFavoriteFragment : BaseFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding : FragmentFavMoviesBinding
    private lateinit var mAdapter : MoviesAdapter
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MoviesFavoriteVM::class.java)
    }

    companion object{
        fun newInstance() = MoviesFavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        initData()
    }

    private fun initUi() {
        mAdapter = MoviesAdapter{
            startActivity<DetailMovieActivity>("movies" to it)
        }

        binding.rvFavMovies.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initData() {
        viewModel.pagedMovies.observe(viewLifecycleOwner, moviesObserver)
    }

    private val moviesObserver =
        Observer<PagedList<ResultsMovies>> { resultMovies ->
            if (resultMovies != null) {
                mAdapter.submitList(resultMovies)
            }
        }
}