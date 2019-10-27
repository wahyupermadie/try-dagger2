package com.wepe.trydagger.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.databinding.MoviesItemBinding

class MoviesAdapter(private val listener : (ResultsMovies) -> Unit) :
    PagedListAdapter<ResultsMovies, MoviesAdapter.ViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MoviesItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movies_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultsItem : ResultsMovies? =  getItem(position)
        holder.binding.populars = resultsItem

        holder.bindItem(resultsItem, listener)
    }

    class ViewHolder(val binding: MoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(resultsItem: ResultsMovies?, listener: (ResultsMovies) -> Unit) {
            itemView.rootView.setOnClickListener {
                resultsItem?.let { it1 -> listener(it1) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<ResultsMovies>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldConcert: ResultsMovies,
                                         newConcert: ResultsMovies) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: ResultsMovies,
                                            newConcert: ResultsMovies) = oldConcert == newConcert
        }
    }
}