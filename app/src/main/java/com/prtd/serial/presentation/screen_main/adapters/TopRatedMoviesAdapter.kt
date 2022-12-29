package com.prtd.serial.presentation.screen_main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.domain.models.MoviesTopRated
import com.prtd.serial.presentation.view_holders.ViewHolder

class TopRatedMoviesAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items: ArrayList<MoviesTopRated.Result>? = null

    fun addItems(newItems: ArrayList<MoviesTopRated.Result>) {
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
        holder.txtRate.text = items!![position].voteAverage.toString()
        holder.txtDate.text = items!![position].releaseDate
        holder.viewItem.setOnClickListener {
            HelperMethods.startMediaActivity(
                context = holder.itemView.context,
                mediaId = items!![position].id,
                mediaName = items!![position].title,
                type = Constants.Type_Movie,
            )
        }
        HelperMethods.loadImageIntoView(
            holder.itemView.context,
            "$Img_Url${items!![position].posterPath}",
            holder.imgPoster
        )

    }

}

