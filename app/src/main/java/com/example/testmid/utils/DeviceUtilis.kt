package com.example.testmid.utils

import android.content.Context
import android.net.ConnectivityManager
object DeviceUtilis {

    fun isDeviceConnectedToInternet(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return (activeNetwork != null)
    }

}