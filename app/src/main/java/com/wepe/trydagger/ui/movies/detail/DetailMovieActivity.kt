package com.wepe.trydagger.ui.movies.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.databinding.ActivityDetailMovieBinding
import dagger.android.AndroidInjection

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        val movies = intent.getParcelableExtra<ResultsMovies>("movies")
        movies?.let {
            binding.movies = it
        }
    }
}
