package com.example.myapplication.model

data class List(
    var main: Main? = Main(),
    var weather: ArrayList<Weather> = arrayListOf(),
    var dt_txt: String? = null)