package com.wepe.trydagger.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    fun dateFormat(value : String) : String{

        val sdf = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        val date = sdf.parse(value)

        val newSdf = SimpleDateFormat("dd-MMM-yyyy", Locale.US)
        return newSdf.format(date!!)
    }

    fun isConnected(ctx: Context?) : Boolean {
        val connectivityManager = ctx?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork != null
        } else {
            connectivityManager.activeNetworkInfo != null
        }
    }
}