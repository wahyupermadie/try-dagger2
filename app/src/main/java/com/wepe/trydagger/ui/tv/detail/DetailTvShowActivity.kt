package com.wepe.trydagger.ui.tv.detail

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
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.databinding.ActivityDetailTvShowBinding
import com.wepe.trydagger.ui.tv.viewmodel.TvShowDetailVM
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailTvShowActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel : TvShowDetailVM
    private lateinit var binding : ActivityDetailTvShowBinding
    private var isFavorite : Boolean = false
    private var menuItem : Menu? = null
    private var tvShow = ResultsTv()
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
        intent.getParcelableExtra<ResultsTv>("tvShow")?.let{
            tvShow = it
        }
        tvShow.let {
            viewModel.addMovies(it)
        }

        supportActionBar?.title = tvShow.originalName
        viewModel.getSingleMovie(tvShow.id)
        viewModel.tv.observe(this, Observer {
            if (it != null){
                isFavorite = it.isFavorite
                setFavorite()
            }
        })

        viewModel.updateFav.observe(this, Observer {})
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.add_to_favorite -> {
                isFavorite = if(!isFavorite){
                    updateFavorite( true, tvShow.id)
                    !isFavorite
                }else{
                    updateFavorite( false, tvShow.id)
                    !isFavorite
                }
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
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
