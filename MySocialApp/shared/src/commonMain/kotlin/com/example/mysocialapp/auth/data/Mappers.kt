package com.example.mysocialapp.auth.data

import com.example.mysocialapp.auth.domain.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, token, followersCount, followingCount)
}