package com.moviesapp.mymoviesapp.ui.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.moviesapp.mymoviesapp.R
import com.moviesapp.mymoviesapp.data.model.CarouselMovie
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.base.PlutoViewHolder
import com.opensooq.pluto.listeners.OnItemClickListener

class CarouselAdapter(items: MutableList<CarouselMovie> , onItemClickListener: OnItemClickListener<CarouselMovie>)
    : PlutoAdapter<CarouselMovie, CarouselAdapter.ViewHolder>(items,onItemClickListener) {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, R.layout.viewpager_item)
    }

    class ViewHolder(parent: ViewGroup, itemLayoutId: Int) : PlutoViewHolder<CarouselMovie>(parent, itemLayoutId) {
        private var ivPoster: ImageView = getView(R.id.iVvpMovie)
        private var tvTitle: TextView = getView(R.id.vpTitle)

        override fun set(item: CarouselMovie, position: Int) {
            Glide.with(context).load(item.posterId).into(ivPoster)
            tvTitle.text = item.title
        }
    }

}