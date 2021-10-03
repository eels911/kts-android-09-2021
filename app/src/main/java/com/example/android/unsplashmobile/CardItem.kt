package com.example.android.unsplashmobile
import java.util.*

data class CardItem (
    val author: String,
    val title: String,
    var votes: Int,
    val uuid: UUID
)