package com.example.countlife.utils.genericFunctions

import com.example.countlife.R

fun getLifeColor(number: Int): Int{
    return when {
        number <= 25 -> {
            R.drawable.custom_button_red
        }
        number <= 100 -> {
            R.drawable.custom_button_green
        }
        else -> {
            R.drawable.custom_button_blue
        }
    }
}