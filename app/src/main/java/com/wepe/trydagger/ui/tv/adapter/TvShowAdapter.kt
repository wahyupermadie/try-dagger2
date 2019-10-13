package com.wepe.trydagger.ui.tv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.databinding.TvShowItemBinding

class TvShowAdapter(private var resultsItem: MutableList<ResultsTv>?, private val listener : (ResultsTv) -> Unit) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {
    fun addData(list: ResultsTv){
        resultsItem?.add(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<TvShowItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.tv_show_item, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.items = resultsItem?.get(position)
        holder.bindItem(resultsItem?.get(position), listener)
    }

    override fun getItemCount(): Int {
        return if(resultsItem?.size == null) 0 else resultsItem!!.size
    }

    class ViewHolder(val binding: TvShowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(resultsItem: ResultsTv?, listener: (ResultsTv) -> Unit) {
            itemView.rootView.setOnClickListener {
                resultsItem?.let { it1 -> listener(it1) }
            }
        }
    }
}