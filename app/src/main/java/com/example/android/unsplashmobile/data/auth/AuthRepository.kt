package com.example.android.unsplashmobile.data.auth

import android.net.Uri
import android.util.Log
import com.example.android.unsplashmobile.data.auth.AuthConfig.ACCESS_KEY
import com.example.android.unsplashmobile.data.auth.AuthConfig.AUTH_URI
import com.example.android.unsplashmobile.data.auth.AuthConfig.REDIRECT_URI
import com.example.android.unsplashmobile.data.auth.AuthConfig.RESPONSE_TYPE
import com.example.android.unsplashmobile.data.auth.AuthConfig.SCOPE
import com.example.android.unsplashmobile.data.auth.AuthConfig.SECRET_KET
import com.example.android.unsplashmobile.data.auth.AuthConfig.TOKEN_URI

import net.openid.appauth.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

class AuthRepository {
    private fun getAuthConfig(): AuthorizationServiceConfiguration =
        AuthorizationServiceConfiguration(
            Uri.parse(AUTH_URI),
            Uri.parse(TOKEN_URI)
        )

    val redirectUri = Uri.parse(AuthConfig.REDIRECT_URI)

    fun getAuthRequest(): AuthorizationRequest =
        AuthorizationRequest.Builder(
            getAuthConfig(),
            ACCESS_KEY,
            RESPONSE_TYPE,
            redirectUri
        )
            .setCodeVerifier(null)
            .setScope(AuthConfig.SCOPE)
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

    private val okHttpClient = buildOkHttpClient()
    private fun buildOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor {
                    Timber.e(it)
                }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )

        if (AuthData.accessToken != null) {
            okHttpClientBuilder.addNetworkInterceptor(
                Interceptor { chain ->
                    val originalRequest = chain.request()

                    val newRequest =
                        originalRequest.newBuilder()
                            .header("Authorization", "Bearer ${AuthData.accessToken!!}")
                            .build()

                    chain.proceed(newRequest)

                }
            )
        }

        return okHttpClientBuilder.build()
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()
}