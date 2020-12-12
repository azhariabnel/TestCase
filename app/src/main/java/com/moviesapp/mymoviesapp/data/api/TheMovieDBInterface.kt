package com.moviesapp.mymoviesapp.data.api

import com.moviesapp.mymoviesapp.data.model.Genre
import com.moviesapp.mymoviesapp.data.model.MovieDetails
import com.moviesapp.mymoviesapp.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>

    @GET("/genre/movie/list")
    fun getAllGenre() : Single<Genre>
}