package com.example.myapplication.model

data class MainModel(
    var cod: String? = null,
    var message: Int? = null,
    var cnt: Int? = null,
    var list: ArrayList<List> = arrayListOf()
)