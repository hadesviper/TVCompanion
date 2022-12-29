package com.prtd.serial.presentation.screen_saved.series_saved_fragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.data.local.entities.EntitySeries
import com.prtd.serial.presentation.view_holders.ViewHolder

class SavedSeriesAdapter(private val items: List<EntitySeries>? = null) :
    RecyclerView.Adapter<ViewHolder>() {


    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items != null) {
            holder.txtName.text = items[position].name
            holder.txtRate.text = items[position].vote.toString()
            holder.txtDate.text = items[position].year
            holder.viewItem.setOnClickListener {
                HelperMethods.startMediaActivity(
                    context = holder.itemView.context,
                    mediaId = items[position].id,
                    mediaName = items[position].name,
                    type = Constants.Type_Series,
                )
            }
            HelperMethods.loadImageIntoView(
                holder.itemView.context,
                "$Img_Url${items[position].posterPath}",
                holder.imgPoster
            )
        }

    }

}

