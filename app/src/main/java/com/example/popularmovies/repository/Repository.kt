package com.example.popularmovies.repository

import com.example.popularmovies.model.MovieResponse
import io.reactivex.Observable

interface Repository {
    fun getMovies(): Observable<MovieResponse>

}