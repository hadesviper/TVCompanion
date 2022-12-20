package com.prtd.serial.presentation.search_screen.series_results.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R
import com.prtd.serial.common.Constants
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.HelperMethods
import com.prtd.serial.domain.models.MultiResult
import com.prtd.serial.presentation.view_holders.ViewHolder

class ResultedSeriesAdapter(private val items: List<MultiResult.Result>): RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items[position].name
        holder.txtRate.text = items[position].vote.toString()
        holder.txtDate.text = items[position].firstAirDate
        holder.viewItem.setOnClickListener{
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

