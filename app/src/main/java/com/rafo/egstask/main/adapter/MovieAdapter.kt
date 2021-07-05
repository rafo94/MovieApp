package com.rafo.egstask.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafo.egstask.databinding.MovieItemBinding
import com.rafo.egstask.util.load
import com.rafo.entities.responseentity.Results

class MovieAdapter(
    private var movieList: MutableList<Results>,
    private val itemClickListener: ((Results) -> Unit)
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.apply {
                movieImage.load(results.posterPath)
                movieName.text = results.originalTitle
                movieName.isSelected = true
                root.setOnClickListener {
                    itemClickListener.invoke(results)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun setData(results: List<Results>) {
        movieList.addAll(results)
        notifyDataSetChanged()
    }
}