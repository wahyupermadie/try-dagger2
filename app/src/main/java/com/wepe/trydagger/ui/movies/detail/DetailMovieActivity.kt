package com.wepe.trydagger.ui.movies.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
    private var isFavorite : Boolean = false
    private var menuItem : Menu? = null
    private var movie = ResultsMovies()
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
            R.id.add_to_favorite -> {
                if (isFavorite){
                    updateFavorite(false, movie.id)
                    isFavorite = !isFavorite
                }else{
                    updateFavorite(true, movie.id)
                    isFavorite = !isFavorite
                }
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        movie = intent.getParcelableExtra<ResultsMovies>("movies") ?: ResultsMovies()
        movie.let {
            viewModel.addMovies(it)
        }

        viewModel.movies.observe(this, Observer {
            binding.movies = it
        })

        viewModel.getSingleMovie(movie.id)
        viewModel.movie.observe(this, Observer {
            if (it != null){
                isFavorite = it.isFavorite!!
                setFavorite()
            }
        })

        viewModel.updateFav.observe(this, Observer {})
    }

    private fun updateFavorite(isFav:Boolean, id:Int){
        viewModel.setFavorite(isFav, id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorite)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite)
        }
    }
}
