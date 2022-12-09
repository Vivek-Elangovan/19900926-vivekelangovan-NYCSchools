package com.android.nycschools

/**
 * This interface is for the click listener
 */
interface ClickListener<T> {
    fun onCLick(data: T)
}