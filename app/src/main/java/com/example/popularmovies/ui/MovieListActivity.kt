package com.example.popularmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularmovies.R
import com.example.popularmovies.model.MovieResponse
import com.example.popularmovies.presenter.MovieListContract
import com.example.popularmovies.presenter.MovieListPresenter
import com.example.popularmovies.repository.MovieListRepository
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(),
    MovieListContract.MovieListView {

    private lateinit var presenter: MovieListContract.MovieListPresenter
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        presenter = MovieListPresenter(this, MovieListRepository())
        presenter.getPopularMovies()

        setupRecyclerView()


    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(mutableListOf())
        rv_movies.adapter = movieListAdapter
        rv_movies.layoutManager = LinearLayoutManager(this)
    }

    override fun displayMovies(movieResponse: MovieResponse) {
        movieListAdapter.setMovieData(movieResponse)
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onDestroyCalled()
        super.onDestroy()
    }
}
