package com.example.android.unsplashmobile.data.auth

import android.net.Uri
import com.example.android.unsplashmobile.data.auth.AuthConfig.ACCESS_KEY
import com.example.android.unsplashmobile.data.auth.AuthConfig.AUTH_URI
import com.example.android.unsplashmobile.data.auth.AuthConfig.REDIRECT_URI
import com.example.android.unsplashmobile.data.auth.AuthConfig.RESPONSE_TYPE
import com.example.android.unsplashmobile.data.auth.AuthConfig.SCOPE
import com.example.android.unsplashmobile.data.auth.AuthConfig.SECRET_KET
import com.example.android.unsplashmobile.data.auth.AuthConfig.TOKEN_URI
import com.example.kts_android_09_2021.network.entities.AuthData
import net.openid.appauth.*

class AuthRepository {
    private fun getServiceConfig(): AuthorizationServiceConfiguration =
        AuthorizationServiceConfiguration(
            Uri.parse(AUTH_URI),
            Uri.parse(TOKEN_URI)
        )

    fun getAuthRequest(): AuthorizationRequest =
        AuthorizationRequest.Builder(
            getServiceConfig(),
            ACCESS_KEY,
            RESPONSE_TYPE,
            Uri.parse(REDIRECT_URI)
        )
            .setCodeVerifier(null)
            .setScope(SCOPE)
            .build()

    fun getAccessToken(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
        onComplete: () -> Unit,
        onError: (AuthorizationException) -> Unit
    ) {
        authService.performTokenRequest(tokenRequest, getClientAuthentication()) { response, ex ->
            if (response != null) {
                val accessToken = response.accessToken.orEmpty()
                AuthData.accessToken = accessToken
                onComplete()
            } else {
                onError(ex!!)
            }
        }
    }

    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(SECRET_KET)
    }
}