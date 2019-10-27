package com.wepe.trydagger.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.wepe.trydagger.databinding.FragmentFavoriteBinding
import com.wepe.trydagger.ui.favorite.movies.MoviesFavoriteFragment
import com.wepe.trydagger.ui.favorite.tvShow.TvShowFavoriteFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

class FragmentFavorite : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding

    private val fragments = listOf(
        MoviesFavoriteFragment.newInstance(),
        TvShowFavoriteFragment.newInstance()
    )

    companion object{
        fun newInstance() = FragmentFavorite()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewpager.adapter = ViewPagerAdapter()
        binding.viewpager.offscreenPageLimit = fragments.size
        tabs.setupWithViewPager(binding.viewpager)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> binding.viewpager.setCurrentItem(0, true)
                    1 -> binding.viewpager.setCurrentItem(1, true)
                }
            }

        })
    }

    private fun setupTitile(s: String) {
        (activity as AppCompatActivity).supportActionBar?.title = s
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(childFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = when(position) {
            0 -> "Movies"
            else -> "Tv Show"
        }
    }
}