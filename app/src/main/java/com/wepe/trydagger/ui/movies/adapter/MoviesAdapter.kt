package com.wepe.trydagger.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wepe.trydagger.R
import com.wepe.trydagger.data.model.ResultsMovies
import com.wepe.trydagger.databinding.MoviesItemBinding

class MoviesAdapter(private var resultsItem: MutableList<ResultsMovies>?, private val listener : (ResultsMovies) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){
    fun addData(list : ResultsMovies){
        resultsItem?.add(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MoviesItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movies_item, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.populars = resultsItem?.get(position)
        holder.bindItem(resultsItem?.get(position), listener)
    }

    override fun getItemCount(): Int {
        return if(resultsItem?.size == null) 0 else resultsItem!!.size
    }

    class ViewHolder(val binding: MoviesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(resultsItem: ResultsMovies?, listener: (ResultsMovies) -> Unit) {
            itemView.rootView.setOnClickListener {
                resultsItem?.let { it1 -> listener(it1) }
            }
        }
    }
}