package com.example.appstore.service

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(val token: String) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // Refresh the access token using your authentication logic
        val newAccessToken = token

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()
    }
}
