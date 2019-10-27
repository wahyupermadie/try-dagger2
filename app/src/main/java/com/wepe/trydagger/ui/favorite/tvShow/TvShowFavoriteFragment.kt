package com.wepe.trydagger.ui.favorite.tvShow

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
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.databinding.FragmentFavTvShowBinding
import com.wepe.trydagger.ui.tv.adapter.TvShowAdapter
import com.wepe.trydagger.ui.tv.detail.DetailTvShowActivity
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class TvShowFavoriteFragment : BaseFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding : FragmentFavTvShowBinding
    private lateinit var viewModel : TvShowFavVM
    private lateinit var mAdapter: TvShowAdapter

    companion object{
        fun newInstance() = TvShowFavoriteFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvShowFavVM::class.java)
        binding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        initData()
    }

    private fun initData() {
        viewModel.tvPaged.observe(viewLifecycleOwner, tvObserver)
    }

    private fun initUi() {
        mAdapter = TvShowAdapter {
            startActivity<DetailTvShowActivity>("tvShow" to it)
        }
        binding.rvFavTvShow.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

    private val tvObserver =
        Observer<PagedList<ResultsTv>> { resultMovies ->
            if (resultMovies != null) {
                mAdapter.submitList(resultMovies)
            }
        }
}