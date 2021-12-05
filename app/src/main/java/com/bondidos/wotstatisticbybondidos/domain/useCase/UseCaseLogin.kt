package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.content.SharedPreferences
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.TOKEN_EXPIRES
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.USER_ACCOUNT_ID
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.USER_NICKNAME
import com.bondidos.wotstatisticbybondidos.domain.constatnts.Constants.USER_TOKEN
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import com.bondidos.wotstatisticbybondidos.domain.other.IsValidUser
import com.bondidos.wotstatisticbybondidos.domain.other.Resource
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val prefs: SharedPreferences,
    private val validate: IsValidUser
    ) {

    suspend fun execute(): Resource<User> {

        val user = User(
            nickname = prefs.getString(USER_NICKNAME, "") ?: "",
            account_id = prefs.getInt(USER_ACCOUNT_ID, -1),
            access_token = prefs.getString(USER_TOKEN, "") ?: "",
            expires_at = prefs.getLong(TOKEN_EXPIRES, -1L)
        )

        return if (validate.execute(user)) {
            Resource.success(user)
        } else Resource.error("User is not valid, please Login", null)
    }
}
