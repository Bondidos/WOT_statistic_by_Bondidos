package com.bondidos.wotstatisticbybondidos.data.sources.sharedPrefs

import android.content.SharedPreferences
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TOKEN_EXPIRES
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.USER_ACCOUNT_ID
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.USER_NICKNAME
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.USER_TOKEN
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class PrefStoreImpl @Inject constructor(
    private val prefs: SharedPreferences
) : PrefsStore {

    override suspend fun saveUser(user: User): Boolean {
        prefs.edit()
            .putString(USER_NICKNAME,user.nickname)
            .putInt(USER_ACCOUNT_ID,user.account_id)
            .putString(USER_TOKEN,user.access_token)
            .putLong(TOKEN_EXPIRES,user.expires_at)
            .apply()
        return true
    }

    override suspend fun getUser(): User {
        return User(
            nickname = prefs.getString(USER_NICKNAME, "") ?: "",
            account_id = prefs.getInt(USER_ACCOUNT_ID, -1),
            access_token = prefs.getString(USER_TOKEN, "") ?: "",
            expires_at = prefs.getLong(TOKEN_EXPIRES, -1L)
        )
    }
}