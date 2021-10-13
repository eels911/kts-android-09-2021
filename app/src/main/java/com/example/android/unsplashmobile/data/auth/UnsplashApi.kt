package com.example.android.unsplashmobile.data.auth

import retrofit2.http.GET

interface UnsplashApi {

    @GET("me")
    suspend fun getUserInfo(): AuthUser
}