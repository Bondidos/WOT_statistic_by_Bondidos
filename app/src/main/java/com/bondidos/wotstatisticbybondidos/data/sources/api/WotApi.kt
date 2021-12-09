package com.bondidos.wotstatisticbybondidos.data.sources.api

import com.bondidos.wotstatisticbybondidos.data.entityes.achievesApi.ApiAchievesResponse
import com.bondidos.wotstatisticbybondidos.data.entityes.userClanApi.ApiClanResponse
import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.ApiDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WotApi {

    @GET("/wot/account/info/")
    suspend fun getUserData(
        @Query("application_id") application_id: String,
        @Query("account_id") account_id: Int,
        @Query("access_token") access_token: String,
        @Query("fields") fields: String?
    ): ApiDataResponse

    @GET("/wot/clans/info/")
    suspend fun getUserClanImage(
        @Query("application_id") application_id: String,
        @Query("clan_id") clan_id: Int,
    ): ApiClanResponse

    @GET("/wot/account/achievements/")
    suspend fun getUserAchieves(
        @Query("application_id") application_id: String,
        @Query("account_id") account_id: Int,
        @Query("fields") fields: String?
    ): ApiAchievesResponse

    @GET("/wot/auth/logout/")
    suspend fun logout(
        @Query("application_id") application_id: String,
        @Query("access_token") access_token: String
    )
}