package com.prtd.serial.presentation.screen_saved.movie_saved_fragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.Constants.Type_Movie
import com.prtd.serial.common.HelperMethods.loadImageIntoView
import com.prtd.serial.common.HelperMethods.startMediaActivity
import com.prtd.serial.data.local.entities.EntityMovie
import com.prtd.serial.presentation.view_holders.ViewHolder

class SavedMoviesAdapter(private val items: List<EntityMovie>? = null) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (items != null) {
            holder.txtName.text = items[position].title
            holder.txtRate.text = items[position].vote.toString()
            holder.txtDate.text = items[position].year
            holder.viewItem.setOnClickListener {
                startMediaActivity(
                    context = holder.itemView.context,
                    mediaId = items[position].id,
                    mediaName = items[position].title,
                    type = Type_Movie,
                )
            }
            loadImageIntoView(
                holder.itemView.context,
                "$Img_Url${items[position].posterPath}",
                holder.imgPoster
            )
        }


    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

}

