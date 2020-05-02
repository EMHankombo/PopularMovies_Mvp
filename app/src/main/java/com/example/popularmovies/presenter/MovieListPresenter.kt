package com.example.popularmovies.presenter

import com.example.popularmovies.model.MovieResponse
import com.example.popularmovies.repository.Repository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieListPresenter(
    private var view: MovieListContract.MovieListView?,
    private val repository: Repository
) :
    MovieListContract.MovieListPresenter {


    private val compositeDisposable = CompositeDisposable()

    override fun getPopularMovies() {
        view?.showProgressBar()

        repository.getMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)


    }

    private val observer = object : Observer<MovieResponse> {
        override fun onComplete() {

        }

        override fun onNext(t: MovieResponse) {
            view?.hideProgressBar()
            view?.displayMovies(t)
        }

        override fun onError(e: Throwable) {
            view?.hideProgressBar()
            view?.showError(e.message)

        }

        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }
    }


    override fun onDestroyCalled() {
        view = null
        compositeDisposable.clear()
    }
}