package com.example.android.unsplashmobile.data.auth

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://unsplash.com/oauth/authorize"
    const val TOKEN_URI = "https://unsplash.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE =
        "public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections"

    //    const val ACCESS_KEY = "-7nybGaaVVinUEfZ1PEtWVHRq_v2rd7KkzsTHZussJw"
//    const val SECRET_KET = "TcyDvd9mD8p2r96ib_WgNKraqg6KN4iE1rIx48y8VOI"
    const val ACCESS_KEY = "n4qEc9phQcNduprQTvhrYFmjUQJ4KRxamBaypg83als"
    const val SECRET_KET = "Y7RnjPgXAi4WjlN8hNrIEcwegv9I9SQ4qkVUcEVDVnI"
    const val REDIRECT_URI = "com.example.kts.android.09.2021:/oath2redirect"

}