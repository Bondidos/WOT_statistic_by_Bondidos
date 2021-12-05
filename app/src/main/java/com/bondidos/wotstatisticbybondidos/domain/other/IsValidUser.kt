package com.bondidos.wotstatisticbybondidos.domain.other

import com.bondidos.wotstatisticbybondidos.domain.entityes.User

class IsValidUser() {
    fun execute(user: User): Boolean {
        return user.account_id != -1 &&
                user.nickname != "" &&
                user.access_token != "" &&
                isExpired(user.expires_at)
    }

    private fun isExpired(expiresAt: Long): Boolean = System.currentTimeMillis() <= expiresAt
}