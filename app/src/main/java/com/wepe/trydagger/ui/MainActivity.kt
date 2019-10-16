package com.wepe.trydagger.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wepe.trydagger.R
import com.wepe.trydagger.databinding.ActivityMainBinding
import com.wepe.trydagger.ui.movies.fragment.MoviesFragment
import com.wepe.trydagger.ui.tv.fragment.TvShowFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener, HasAndroidInjector{
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private lateinit var binding : ActivityMainBinding
    private val fragments = listOf(
        MoviesFragment.newInstance(),
        TvShowFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbarProfile)
        binding.nsvpContent.addOnPageChangeListener(this)
        binding.nsvpContent.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.nsvpContent.offscreenPageLimit = fragments.size
        binding.navigation.setOnNavigationItemSelectedListener(this)

        supportActionBar?.title = getString(R.string.movies_label)
        binding.navigation.selectedItemId = 0
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    @SuppressLint("ResourceType")
    override fun onPageSelected(position: Int) {
        when(position){
            0 -> {
                supportActionBar?.title = getString(R.string.movies_label)
                binding.navigation.selectedItemId = 0
            }
            1 -> {
                supportActionBar?.title = getString(R.string.tv_show_label)
                binding.navigation.selectedItemId = 1
            }
            else -> {
                supportActionBar?.title = getString(R.string.movies_label)
                binding.navigation.selectedItemId = 0
            }
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.movies_menu->{
                binding.nsvpContent.currentItem = 0
            }
            R.id.tv_menu->{
                binding.nsvpContent.currentItem = 1
            }
        }
        return true
    }


    inner class ViewPagerAdapter(fragmentManager : FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size
    }
}
