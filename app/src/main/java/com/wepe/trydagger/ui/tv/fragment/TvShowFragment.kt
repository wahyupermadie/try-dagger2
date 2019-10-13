package com.wepe.trydagger.ui.tv.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.wepe.trydagger.MainApplication
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.base.BaseViewModel
import com.wepe.trydagger.databinding.FragmentTvShowBinding
import com.wepe.trydagger.di.viewmodel.ViewModelFactory
import com.wepe.trydagger.ui.tv.adapter.TvShowAdapter
import com.wepe.trydagger.ui.tv.viewmodel.TvShowViewModel
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

    override fun onAttach(context: Context) {
        (activity?.application as MainApplication).appComponent.inject(this)
        super.onAttach(context)
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
                it.results?.forEach {tv ->
                    mAdapter.addData(tv)
                }
            }
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun initUi() {
        mAdapter = TvShowAdapter(arrayListOf()){

        }
        binding.rvTvShow.apply {
            this.adapter = mAdapter
            this.layoutManager = LinearLayoutManager(context)
        }
    }

}