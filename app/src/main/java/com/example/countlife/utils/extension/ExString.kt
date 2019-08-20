package com.example.countlife.utils.extension

import com.example.countlife.R

enum class Value(val VALUE: Int) {
    RED(40),
    GREEN(100),
}

fun String.countResult(operator: String, total: Int): String {
    val intValue = this.toInt()
    return when(operator){
        "+" -> {(intValue + total).toString()}
        "-" -> {(intValue - total).toString()}
        else -> {(intValue + total).toString()}
    }
}

fun String.returnLifeColor(): Int{
    val number = this.toInt()
    return when {
        number <= Value.RED.VALUE -> {
            R.drawable.custom_button_red
        }
        number <= Value.GREEN.VALUE -> {
            R.drawable.custom_button_green
        }
        else -> {
            R.drawable.custom_button_blue
        }
    }
}