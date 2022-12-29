package com.prtd.serial.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.prtd.serial.R
import com.prtd.serial.common.Constants.Type_Series
import com.prtd.serial.presentation.screen_item_movie.MovieActivity
import com.prtd.serial.presentation.screen_item_series.SeriesActivity
import com.prtd.serial.presentation.screen_search.SearchActivity
import kotlin.math.pow
import kotlin.system.exitProcess

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
            .error(getDrawable(context, R.drawable.not_found))
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
        if (result.text.toString().isNotBlank()) {
            Intent(
                context,
                SearchActivity::class.java
            ).run {
                this.putExtra(Constants.Media_Name, result.text.toString())
                Log.i("TAG", "startSearchActivity: ${result.text}")
                context.startActivity(this)
            }
        }

    }

    fun showErrorDialog(context: Context, message: String, retryFun: () -> Unit) {
        MaterialAlertDialogBuilder(context)
            .setMessage("Error message: $message")
            .setTitle("An error has occurred")
            .setPositiveButton("Retry!") { _, _ ->
                retryFun.invoke()
            }
            .setNegativeButton("Dismiss!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getColor(context, R.color.red))
                getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(context, R.color.red))
            }
    }

    fun showExitDialog(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setMessage("Are you sure about exiting?")
            .setTitle("Exit?")
            .setPositiveButton("Yes!") { _, _ ->
                exitProcess(-1)
            }
            .setNegativeButton("No!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getColor(context, R.color.red))
                getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(context, R.color.red))
            }
    }

    fun showAboutDialog(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setMessage(
                HtmlCompat.fromHtml(
                    "<br><br>Developed by <u><font color=\"white\">Ibrahim Abdin</></u> 😁 <br><br>Independent Android app & game developer.<br><br> <a href=\"https://www.facebook.com/ibrahim.abdin.2\">Facebook</a><br><br><a href=\"https://www.linkedin.com/in/ibrahim-abdin-7ab463169/\">LinkedIn</a>",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            )
            .setTitle("About!")
            .setPositiveButton("Ok!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getColor(context, R.color.red))
                getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(context, R.color.red))
                (findViewById<View>(android.R.id.message) as TextView).movementMethod =
                    LinkMovementMethod.getInstance()
            }
    }

    fun recyclerViewsHandler(rv: RecyclerView, condition: () -> Boolean, action: () -> Unit) {
        rv.run {
            addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        Log.i("TAG", "onScrollStateChanged: scroll changed")
                        super.onScrollStateChanged(recyclerView, newState)
                        if (condition()) {
                            Log.i("TAG", "onScrollStateChanged: it Should Load")
                            action()
                        }
                    }
                }
            )
        }
    }
}