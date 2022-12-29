package com.prtd.serial.presentation.screen_item_movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.Constants.Type_Movie
import com.prtd.serial.common.HelperMethods.loadImageIntoView
import com.prtd.serial.common.HelperMethods.startMediaActivity
import com.prtd.serial.domain.models.MoviesResult
import com.prtd.serial.presentation.view_holders.ViewHolder

class SimilarMoviesAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: ArrayList<MoviesResult.Result>? = null

    fun addItems(newItems: ArrayList<MoviesResult.Result>) {
        if (items == null) {
            items = ArrayList(newItems)
        } else {
            items!!.addAll(newItems)
        }
        notifyItemInserted(items!!.size - newItems.size)
    }


    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items!![position].title
        holder.txtRate.text = "${items!![position].voteAverage}"
        holder.txtDate.text = items!![position].releaseDate
        holder.viewItem.setOnClickListener {
            startMediaActivity(
                context = holder.itemView.context,
                mediaId = items!![position].id,
                mediaName = items!![position].title,
                type = Type_Movie,
            )
        }
        loadImageIntoView(
            holder.itemView.context,
            "$Img_Url${items!![position].posterPath}",
            holder.imgPoster
        )
    }

}

