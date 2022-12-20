package com.prtd.serial.presentation.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.prtd.serial.R

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val txtName: TextView = itemView.findViewById(R.id.txtName)
    val txtRate: TextView = itemView.findViewById(R.id.txtRate)
    val txtDate: TextView = itemView.findViewById(R.id.txtDate)
    val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
    val viewItem: CardView = itemView.findViewById(R.id.viewItem)
}