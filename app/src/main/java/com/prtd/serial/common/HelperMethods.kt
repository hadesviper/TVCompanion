package com.prtd.serial.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.prtd.serial.R
import com.prtd.serial.common.Constants.Type_Series
import com.prtd.serial.presentation.item_movie.MovieActivity
import com.prtd.serial.presentation.item_series.SeriesActivity
import com.prtd.serial.presentation.search_screen.SearchActivity
import kotlin.math.pow

object HelperMethods {
    fun roundToDecimalPlaces(value: Float,place:Int):Float{

        return (((value+(0.5/10.0.pow(place)))*10.0.pow(place)).toInt())/10.0.pow(place).toFloat()
    }

    fun openYoutubeLink(context: Context, youtubeID: String) {
        if (youtubeID.isBlank()){
            Toast.makeText(context, "No trailer was found", Toast.LENGTH_SHORT).show()
        }else{
            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.youtube.com/watch?v=$youtubeID"))
            context.startActivity(intentApp)
        }
    }

    fun loadImageIntoView(context: Context,path: String,imgView: ImageView){
        Glide.with(context)
            .load(path)
            .error(ContextCompat.getDrawable(context, R.drawable.not_found))
            .into(imgView)
    }

    fun startMediaActivity(context: Context,mediaId: Int,mediaName: String,type:String ) {
        Intent(
            context,
            if (type == Type_Series) SeriesActivity::class.java else MovieActivity::class.java).run {
            this.putExtra(Constants.Media_ID, mediaId)
            this.putExtra(Constants.Media_Name, mediaName)
            context.startActivity(this)
        }
    }

    fun startSearchActivity(context: Context,result: AutoCompleteTextView) {
        Intent(
            context,
            SearchActivity::class.java).run {
            this.putExtra(Constants.Media_Name, result.text.toString())
            Log.i("TAG", "startSearchActivity: ${result.text}")
            context.startActivity(this)
        }
    }
}