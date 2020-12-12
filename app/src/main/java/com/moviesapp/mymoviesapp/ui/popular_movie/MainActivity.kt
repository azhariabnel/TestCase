package com.moviesapp.mymoviesapp.ui.popular_movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.moviesapp.mymoviesapp.R
import com.moviesapp.mymoviesapp.data.api.TheMovieDBClient
import com.moviesapp.mymoviesapp.data.api.TheMovieDBInterface
import com.moviesapp.mymoviesapp.data.model.CarouselMovie
import com.moviesapp.mymoviesapp.data.model.Movie

import com.moviesapp.mymoviesapp.data.repository.NetworkState
import com.moviesapp.mymoviesapp.ui.adapter.CarouselAdapter
import com.opensooq.pluto.PlutoView
import com.opensooq.pluto.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    lateinit var movieRepository: MoviePagedListRepository
    private fun getCvMovies(): MutableList<CarouselMovie>{
        val items = mutableListOf<CarouselMovie>()
        items.add(CarouselMovie("Endgame",R.drawable.ic_end_game))
        items.add(CarouselMovie("Captain Marvel",R.drawable.ic_captain_marvel))
        items.add(CarouselMovie("Iron Man",R.drawable.ic_iron_man))
        items.add(CarouselMovie("Doctor Strange",R.drawable.ic_dr_strange))
        return items
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService : TheMovieDBInterface = TheMovieDBClient.getClient()
        setTopBar()
        tvMovies1.setText(getString(R.string.popular_movies))
        tvMovies.setText(getString(R.string.new_release))

        movieRepository = MoviePagedListRepository(apiService)

        viewModel = getViewModel()

        val movieAdapter = PopularMoviePagedListAdapter(this)

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return  1
                else return 3
            }
        };
        /*spinnerGenre.setItems(resources.getStringArray(R.array.GenreArrays))
        spinnerGenre.setTitle("Select Genre")*/
        setCarousel()


        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(false)
        rv_movie_list.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_popular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error_popular.visibility =
                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })

    }

    private fun setCarousel() {
        val pluto = findViewById<PlutoView>(R.id.slider_view)
        val adapter = CarouselAdapter(getCvMovies(), object : OnItemClickListener<CarouselMovie> {
            override fun onItemClicked(item: CarouselMovie?, position: Int) {

            }

        })

        pluto.create(adapter, lifecycle = lifecycle)
    }

    private fun setTopBar() {
        val prfs = getSharedPreferences("Users", Context.MODE_PRIVATE)
        val nickname = prfs.getString("Nickname", "")
        val calendar = Calendar.getInstance()
        val timeOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        if (timeOfDay >= 0 && timeOfDay < 12) {
            tvHello?.setText("Good morning, $nickname")
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            tvHello?.setText("Good afternoon, $nickname")
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            tvHello?.setText("Good afternoon, $nickname")
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            tvHello?.setText("Good evening, $nickname")
        }
    }


    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(movieRepository) as T
            }
        })[MainActivityViewModel::class.java]
    }

}
