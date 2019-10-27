package com.wepe.trydagger.ui.favorite.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fm : FragmentManager, fragmentList: ArrayList<Fragment>, val context: Context) : FragmentStatePagerAdapter(fm){

    private var fragments = ArrayList<Fragment>()

    init {
        fragments = fragmentList
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Movies"
        else -> "Tv Show"
    }

    override fun getCount(): Int = 4
}