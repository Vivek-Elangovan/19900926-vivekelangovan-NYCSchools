package com.android.nycschools

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * This class is for the utility function used in the application
 */
class Utils {
    companion object {
        private var isNetworkAvailableOverride: Boolean? = null

        /**
         * This function will handle the network availability
         * @return Boolean
         */
        @RequiresApi(Build.VERSION_CODES.M)
        fun isNetworkAvailable(context: Context): Boolean {
            return isNetworkAvailableOverride?.let {
                isNetworkAvailableOverride
            } ?: run {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                cm.activeNetwork?.let { n ->
                    cm.getNetworkCapabilities(n)?.let { nc ->
                        return nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                            NetworkCapabilities.TRANSPORT_WIFI
                        )
                    }
                }
                return false
            }
        }
    }
}