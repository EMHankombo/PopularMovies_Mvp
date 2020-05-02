package com.example.popularmovies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.R
import com.example.popularmovies.model.MovieResponse
import com.example.popularmovies.model.Result
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter(private var movieResponse: MutableList<Result>): RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)

        return MovieViewHolder(view)
    }

    fun setMovieData(movies: MovieResponse){
        movieResponse = movies.results.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieResponse.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.title.text = movieResponse[position].title
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.tv_title

    }
}