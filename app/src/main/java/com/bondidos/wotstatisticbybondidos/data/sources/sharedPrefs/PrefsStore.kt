package com.bondidos.wotstatisticbybondidos.data.sources.sharedPrefs

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

interface PrefsStore {

    suspend fun saveUser(user: User): Boolean

    suspend fun getUser(): User?

    suspend fun logout()
}