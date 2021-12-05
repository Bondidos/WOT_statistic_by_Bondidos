package com.bondidos.wotstatisticbybondidos.data.sharedPrefs

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

interface PrefsStore {

    fun saveUser(user: User)

    fun getUser(): User?
}