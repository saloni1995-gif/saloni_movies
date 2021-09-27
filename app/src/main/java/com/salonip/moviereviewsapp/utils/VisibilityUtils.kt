package com.salonip.moviereviewsapp.utils

import android.view.View

// This method is used for Visible and Invisible view
fun visibilities(invisible: View?, visible: View?) {
    invisible?.visibility = View.INVISIBLE
    visible?.visibility  = View.VISIBLE
}