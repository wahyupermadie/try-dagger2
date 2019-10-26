package com.wepe.trydagger.ui.tv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.databinding.FragmentTvShowBinding
import com.wepe.trydagger.ui.tv.adapter.TvShowAdapter
import com.wepe.trydagger.ui.tv.detail.DetailTvShowActivity
import com.wepe.trydagger.ui.tv.viewmodel.TvShowViewModel
import com.wepe.trydagger.utils.EspressoIdlingResource
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class TvShowFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding : FragmentTvShowBinding
    private lateinit var viewModel : TvShowViewModel
    private lateinit var mAdapter: TvShowAdapter
    companion object {
        fun newInstance() : TvShowFragment{
            return TvShowFragment()
        }
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvShowViewModel::class.java)
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        initData()
    }

    private fun initData() {
        viewModel.getTvShow(1)
        viewModel.tvShow.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.forEach {tv ->
                    mAdapter.addData(tv)
                }
            }
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun initUi() {
        mAdapter = TvShowAdapter(arrayListOf()){
            startActivity<DetailTvShowActivity>("tvShow" to it)
        }
        binding.rvTvShow.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

}