package com.bondidos.wotstatisticbybondidos.domain.useCase

import android.net.UrlQuerySanitizer
import android.util.Log
import com.bondidos.wotstatisticbybondidos.domain.Repository
import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import javax.inject.Inject

class UseCaseSaveUser @Inject constructor(private val repository: Repository) {

    suspend fun execute(url: String): Boolean{

        val user = getUserFromUrl(url)
        Log.d("UseCaseSaveUser", user.toString())
        if (user != null) {
                return repository.saveUserToCash(user) == user.account_id.toLong()
            }
        return false
    }

    private fun getUserFromUrl(url: String): User? {
        val sanitizer = UrlQuerySanitizer().apply {
            allowUnregisteredParamaters = true
            parseUrl(url)
        }
        return if(sanitizer.getValue("status") == "ok")
            User(
                nickname = sanitizer.getValue("nickname"),
                account_id = sanitizer.getValue("account_id").toInt(),
                access_token = sanitizer.getValue("access_token"),
                expires_at = sanitizer.getValue("expires_at").toLong()
            )
        else null
    }
}