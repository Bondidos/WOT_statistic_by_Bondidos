package com.bondidos.wotstatisticbybondidos.data.sharedPrefs

import android.content.SharedPreferences
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class PrefStoreImpl @Inject constructor(
    private val prefs: SharedPreferences
) : PrefsStore {

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUser(): User {
        return User(
            nickname = prefs.getString(Constants.USER_NICKNAME, "") ?: "",
            account_id = prefs.getInt(Constants.USER_ACCOUNT_ID, -1),
            access_token = prefs.getString(Constants.USER_TOKEN, "") ?: "",
            expires_at = prefs.getLong(Constants.TOKEN_EXPIRES, -1L)
        )
    }
}