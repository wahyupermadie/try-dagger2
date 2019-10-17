package com.wepe.trydagger.ui.tv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wepe.trydagger.BuildConfig
import com.wepe.trydagger.base.BaseFragment
import com.wepe.trydagger.data.model.ResponseTv
import com.wepe.trydagger.databinding.FragmentTvShowBinding
import com.wepe.trydagger.ui.tv.adapter.TvShowAdapter
import com.wepe.trydagger.ui.tv.detail.DetailTvShowActivity
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class TvShowFragment : BaseFragment(), TvShowContract.View {
    @Inject
    lateinit var presenter : TvShowPresenter

    private lateinit var binding : FragmentTvShowBinding
    private lateinit var mAdapter: TvShowAdapter
    companion object {
        fun newInstance() : TvShowFragment{
            return TvShowFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.onAttachView(this)
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onTvShowSuccess(responseTv: ResponseTv) {
        responseTv.results?.forEach {tv ->
            mAdapter.addData(tv)
        }

        mAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        initData()
    }

    private fun initData() {
        presenter.fetchTvShow(1, BuildConfig.API_KEY)
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