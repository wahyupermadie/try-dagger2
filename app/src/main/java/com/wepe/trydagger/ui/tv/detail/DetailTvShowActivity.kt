package com.wepe.trydagger.ui.tv.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.databinding.ActivityDetailTvShowBinding
import dagger.android.AndroidInjection

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_show)
//        binding.lifecycleOwner = this

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
            binding.tvShow = it
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
