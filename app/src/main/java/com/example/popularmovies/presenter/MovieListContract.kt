package com.example.popularmovies.presenter

import com.example.popularmovies.model.MovieResponse

interface MovieListContract {

    interface MovieListPresenter{

        fun getPopularMovies()

        fun onDestroyCalled()
    }

    interface MovieListView{

        fun displayMovies(movieResponse: MovieResponse)

        fun showError(message:String?)

        fun showProgressBar()

        fun hideProgressBar()
    }
}