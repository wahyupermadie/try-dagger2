package com.wepe.trydagger.ui.tv.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.databinding.ActivityDetailTvShowBinding
import com.wepe.trydagger.di.viewmodel.ViewModelFactory
import com.wepe.trydagger.ui.tv.viewmodel.TvShowDetailVM
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailTvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel : TvShowDetailVM
    private lateinit var binding : ActivityDetailTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TvShowDetailVM::class.java)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_show)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initUi()
        initData()
    }

    private fun initUi() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        val tvShow = intent.getParcelableExtra<ResultsTv>("tvShow")
        tvShow?.let {
            viewModel.addMovies(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
