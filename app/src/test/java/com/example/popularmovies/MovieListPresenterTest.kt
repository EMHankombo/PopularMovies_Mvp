package com.example.popularmovies

import com.example.popularmovies.model.MovieResponse
import com.example.popularmovies.model.Result
import com.example.popularmovies.presenter.MovieListContract
import com.example.popularmovies.presenter.MovieListPresenter
import com.example.popularmovies.repository.Repository
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieListPresenterTest {

    @Mock
    private lateinit var view: MovieListContract.MovieListView

    @Mock
    private lateinit var repository: Repository

    private lateinit var presenter: MovieListContract.MovieListPresenter

    private lateinit var response: MovieResponse

    @Before
    fun setup() {

        presenter = MovieListPresenter(view, repository)
        response = MovieResponse(
            1, 1, 1, listOf(
                Result(
                    1.1, 1, true,
                    "Pic1", 1, true, "largePic", "en", "hey", listOf(1, 2), "hey",
                    1.1, "123", "00"
                )
            )
        )

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `get popular movies successfully returns movie list`() {
        Mockito.`when`(repository.getMovies()).thenReturn(Observable.just(response))

        presenter.getPopularMovies()

        Mockito.verify(view).displayMovies(response)
    }

    @Test
    fun `when get popular movies returns with an error view should show error`() {
        val error = "Error message"
        Mockito.`when`(repository.getMovies()).thenReturn(Observable.error(RuntimeException(error)))

        presenter.getPopularMovies()


        Mockito.verify(view).showError(error)
    }


}