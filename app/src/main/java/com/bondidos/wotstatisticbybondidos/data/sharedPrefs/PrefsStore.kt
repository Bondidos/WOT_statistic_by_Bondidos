package com.bondidos.wotstatisticbybondidos.data.sharedPrefs

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

interface PrefsStore {

    suspend fun saveUser(user: User)

    suspend fun getUser(): User?
}