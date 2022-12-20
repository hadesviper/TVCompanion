package com.prtd.serial.presentation.screen_item_series.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.prtd.serial.R
import com.prtd.serial.common.Constants.Img_Url
import com.prtd.serial.common.HelperMethods.loadImageIntoView
import com.prtd.serial.domain.models.Series
import com.prtd.serial.presentation.view_holders.ViewHolder

class SeasonsAdapter(private val items:Series): RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.seasons.size
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items.seasons[position].name
        holder.txtRate.setCompoundDrawables(null,null,null,null)
        "Episodes: ${items.seasons[position].episodeCount}".also { holder.txtRate.text = it }
        holder.txtDate.text = items.seasons[position].airDate
        holder.viewItem.setOnClickListener{
            if (items.seasons[position].overview.isBlank()){
                Toast.makeText(holder.itemView.context, "No Overview!", Toast.LENGTH_SHORT).show()
            }else{
                MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Overview")
                    .setMessage(items.seasons[position].overview)
                    .show()
            }
        }
        loadImageIntoView(holder.itemView.context,"$Img_Url${items.seasons[position].posterPath}",holder.imgPoster)
    }
}

