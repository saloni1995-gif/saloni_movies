package com.salonip.moviereviewsapp.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.salonip.moviereviewsapp.R

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.fade_in)
    .setExitAnim(R.anim.fade_out)
    .setPopEnterAnim(R.anim.fade_in)
    .setPopExitAnim(R.anim.fade_out)
    .build()

//Added navigateActionDirectionWithAnimation extension function in NavController class
fun NavController.navigateActionDirectionWithAnimation(directions: NavDirections) =
    this.navigate(directions, navOptions)