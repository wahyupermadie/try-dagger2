package com.wepe.trydagger.ui.tv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsTv
import com.wepe.trydagger.databinding.TvShowItemBinding

class TvShowAdapter(private val listener : (ResultsTv) -> Unit) :
    PagedListAdapter<ResultsTv, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<TvShowItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.tv_show_item, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.items = getItem(position)
        holder.bindItem(getItem(position), listener)
    }

    class ViewHolder(val binding: TvShowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(resultsItem: ResultsTv?, listener: (ResultsTv) -> Unit) {
            itemView.rootView.setOnClickListener {
                resultsItem?.let { it1 -> listener(it1) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<ResultsTv>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldConcert: ResultsTv,
                                         newConcert: ResultsTv) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: ResultsTv,
                                            newConcert: ResultsTv) = oldConcert == newConcert
        }
    }
}