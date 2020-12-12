package com.moviesapp.mymoviesapp.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.moviesapp.mymoviesapp.data.api.TheMovieDBInterface
import com.moviesapp.mymoviesapp.data.repository.MovieDetailsNetworkDataSource
import com.moviesapp.mymoviesapp.data.repository.NetworkState
import com.moviesapp.mymoviesapp.data.model.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }



}