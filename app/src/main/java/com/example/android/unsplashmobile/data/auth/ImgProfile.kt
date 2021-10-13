package com.example.android.unsplashmobile.data.auth

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImgProfile (
    var small: String?,
    var medium: String?,
    var large: String?
)
