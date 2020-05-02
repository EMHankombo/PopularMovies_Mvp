package com.example.popularmovies.repository

import com.example.popularmovies.common.API_KEY
import com.example.popularmovies.model.MovieResponse
import com.example.popularmovies.network.RetrofitInstance
import io.reactivex.Observable

class MovieListRepository: Repository{

    private val call = RetrofitInstance.getRetrofitClient().getPopularMovies(API_KEY)
    override fun getMovies(): Observable<MovieResponse> {

        return call
    }


}