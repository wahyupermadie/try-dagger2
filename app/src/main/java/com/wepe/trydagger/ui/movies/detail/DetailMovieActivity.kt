package com.wepe.trydagger.ui.movies.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.databinding.ActivityDetailMovieBinding
import com.wepe.trydagger.ui.movies.viewmodel.DetailMoviesVM
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailMovieActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel : DetailMoviesVM
    private lateinit var binding : ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMoviesVM::class.java)

        initUi()
        initData()
    }

    private fun initUi() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            viewModel.addMovies(it)
        }

        viewModel.movies.observe(this, Observer {
            binding.movies = it
        })
    }
}
