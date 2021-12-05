package com.bondidos.wotstatisticbybondidos.data.api

import com.bondidos.wotstatisticbybondidos.data.entityes.userDataApi.UserDataApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val FUNCTION_SEARCH = "/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167"

interface WotApi {

   /* @GET("/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=LegitimateKiller")
    suspend fun searchUser() : SearchResponse

    @GET("/wot/account/list/?application_id=5d489c586717c2b76ade8bea16607167&search=legitim")
    fun getStatus():Response<String>

    @GET("/wot/account/achievements/")
    suspend fun getUserAchieves(
        @Query("application_id") application_id: String,
        @Query("account_id") account_id: Int
        ): AchievesResponse*/

    @GET("/wot/account/info/?application_id=5d489c586717c2b76ade8bea16607167&access_token=b98830431fc22d1565bfc3a84f59077f8baa0aff&account_id=560508396&extra=statistics.epic%2C+statistics.random&fields=-statistics.frags%2C+-statistics.clan%2C+-statistics.regular_team%2C+-statistics.company%2C+-statistics.historical%2C+-statistics.team")
    suspend fun getUserData(
        /*@Query("application_id") application_id: String,
        @Query("access_token") access_token: String,
        @Query("account_id") account_id: Int,
        @Query("extra") extra: String?,
        @Query("fields") fields: String?*/
    ): UserDataApi



    /*@GET("/wot/account/achievements/?application_id=5d489c586717c2b76ade8bea16607167&account_id=560508396")
    suspend fun getUserAchieves(): AchievesResponse*/
}