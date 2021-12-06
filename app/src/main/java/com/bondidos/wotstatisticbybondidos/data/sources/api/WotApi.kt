package com.bondidos.wotstatisticbybondidos.data.sources.api

import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.ApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val FUNCTION_SEARCH = "/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167"

interface WotApi {

    @GET("/wot/account/info/")
    suspend fun getUserData(
        @Query("application_id") application_id: String,
        @Query("account_id") account_id: Int,/*
        @Query("extra") extra: String?,*/
        @Query("access_token") access_token: String,
        @Query("fields") fields: String?
    ): ApiDataResponse

}