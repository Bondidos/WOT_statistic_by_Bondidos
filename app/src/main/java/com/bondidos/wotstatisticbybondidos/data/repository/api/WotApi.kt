package com.bondidos.wotstatisticbybondidos.data.repository.api

import com.bondidos.wotstatisticbybondidos.data.entiyes.searchUser.SearchResponse
import retrofit2.Response
import retrofit2.http.GET

private const val FUNCTION_SEARCH = "/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167"

interface WotApi {

    @GET("/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=LegitimateKiller")
    suspend fun searchUser() : SearchResponse

    @GET("/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=legitim")
    fun getStatus():Response<String>
}