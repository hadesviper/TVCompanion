package com.prtd.serial.presentation.main_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.domain.models.MoviesTopRated
import com.prtd.serial.presentation.view_holders.ViewHolder

class TopRatedMoviesAdapter(private val items:MoviesTopRated): RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.results.size
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items.results[position].title
        holder.txtRate.text = items.results[position].voteAverage.toString()
        holder.txtDate.text = items.results[position].releaseDate
        holder.viewItem.setOnClickListener {
            HelperMethods.startMediaActivity(
                context = holder.itemView.context,
                mediaId = items.results[position].id,
                mediaName = items.results[position].title,
                type = Constants.Type_Movie,
            )
        }
        HelperMethods.loadImageIntoView(
            holder.itemView.context,
            "$Img_Url${items.results[position].posterPath}",
            holder.imgPoster
        )

    }

}

