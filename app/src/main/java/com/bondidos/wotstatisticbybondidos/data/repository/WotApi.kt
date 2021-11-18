package com.bondidos.wotstatisticbybondidos.data.repository

import com.bondidos.wotstatisticbybondidos.domain.entityes.User
import retrofit2.http.GET
import retrofit2.http.Query

private const val FUNCTION_SEARCH = "/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=Legitim"

interface WotApi {

    @GET(FUNCTION_SEARCH)
    fun searchUser(search: String) : List<WotUser>
}