package com.wepe.trydagger.ui.movies.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.data.model.ResponseMovies
import com.wepe.trydagger.databinding.FragmentMoviesBinding
import com.wepe.trydagger.ui.movies.adapter.MoviesAdapter
import com.wepe.trydagger.ui.movies.detail.DetailMovieActivity
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class MoviesFragment : BaseFragment(), MoviesContract.View{
    @Inject
    lateinit var moviePresenter: MoviesPresenter

    private lateinit var binding : FragmentMoviesBinding
    private lateinit var mAdapter: MoviesAdapter
    companion object {

        fun newInstance() : MoviesFragment {
            return MoviesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviePresenter.onAttachView(this)
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        initData()
    }

    private fun initData() {
        moviePresenter.getMovies(1, BuildConfig.API_KEY)
    }

    private fun initUi() {
        mAdapter = MoviesAdapter(arrayListOf()){
            startActivity<DetailMovieActivity>("movies" to it)
        }

        binding.rvMovies.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(context)
        }

    }

    override fun onMoviesSuccess(responseMovies: ResponseMovies) {
        responseMovies.results?.forEach {
            mAdapter.addData(it)
        }
        mAdapter.notifyDataSetChanged()
    }
}