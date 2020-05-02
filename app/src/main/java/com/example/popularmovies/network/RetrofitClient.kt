package com.example.popularmovies.network

import com.example.popularmovies.common.POPULAR_MOVIES
import com.example.popularmovies.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitClient {

    @GET(POPULAR_MOVIES)
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

}